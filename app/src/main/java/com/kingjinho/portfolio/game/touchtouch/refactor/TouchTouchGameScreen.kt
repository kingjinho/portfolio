package com.kingjinho.portfolio.game.touchtouch.refactor

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kingjinho.portfolio.R
import com.kingjinho.portfolio.game.GameStatus
import kotlin.random.Random

@Composable
fun TouchTouchGameScreen(
    modifier: Modifier = Modifier,
    viewModel: TouchTouchGameViewModel = TouchTouchGameViewModel()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        if (viewModel.gameStatus.value == GameStatus.Ready) {
            TouchTouchReadyScreen(
                modifier = modifier
                    .fillMaxSize(),
                onClickStart = {
                    viewModel.startGame()
                }
            )
        }

        if (viewModel.gameStatus.value == GameStatus.Playing) {
            val remainingTime = viewModel.remainingTime.value
            val score = viewModel.score.value
            val numbersOfCatToDraw = viewModel.numberOfCatsToDraw.value
            TouchTouchPlayingScreen(
                modifier = modifier
                    .fillMaxSize(),
                score = score,
                remainingTime = remainingTime,
                numbersOfCatToDraw = numbersOfCatToDraw,
                onTouchCat = {
                    viewModel.incrementScore()
                }
            )
        }

        if (viewModel.gameStatus.value == GameStatus.GameOver) {
            TouchTouchGameOverScreen(
                modifier = modifier
                    .fillMaxSize(),
                onClickRestart = {
                    viewModel.resetGame()
                }
            )
        }
    }
}

@Composable
private fun TouchTouchReadyScreen(
    modifier: Modifier = Modifier,
    onClickStart: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.text_touch_touch_game_explanation),
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onClickStart,
        ) {
            Text(
                text = stringResource(id = R.string.text_touch_touch_game_start),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }
}

@Composable
private fun TouchTouchPlayingScreen(
    modifier: Modifier = Modifier,
    score: Int = 0,
    remainingTime: Long = 0,
    numbersOfCatToDraw: Int = 20,
    onTouchCat: () -> Unit
) {
    Column(modifier = modifier.padding(20.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                stringResource(
                    id = R.string.text_touch_touch_game_current_score,
                    score
                ),
                color = Color.White,
            )

            Text(
                stringResource(
                    id = R.string.text_touch_touch_game_remaining_time,
                    remainingTime / 1000L
                ),
                color = Color.White,
                modifier = Modifier
            )
        }

        Box(modifier = Modifier.weight(1f)) {
            repeat(numbersOfCatToDraw) {
                CuteCat(
                    modifier = Modifier.layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(placeable.width, placeable.height) {
                            placeable.place(
                                Random.nextInt(0, constraints.maxWidth - 100),
                                Random.nextInt(0, constraints.maxHeight - 100)
                            )
                        }
                    },
                    onTouchCat = onTouchCat
                )
            }
        }
    }
}

@Composable
private fun CuteCat(
    modifier: Modifier = Modifier,
    onTouchCat: () -> Unit
) {
    var shouldShowCat by rememberSaveable {
        mutableStateOf(true)
    }
    AnimatedVisibility(
        visible = shouldShowCat
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_cat),
            contentDescription = "Touchable Cat",
            modifier = modifier
                .wrapContentSize()
                .size(64.dp)
                .clickable {
                    onTouchCat()
                    shouldShowCat = false
                }
        )
    }
}

@Composable
private fun TouchTouchGameOverScreen(
    modifier: Modifier = Modifier,
    prevScore: Int = 0,
    curScore: Int = 0,
    onClickRestart: () -> Unit
) {
    val gameOverScreenTitleMessage = if (prevScore < curScore) {
        stringResource(id = R.string.text_touch_touch_game_new_record)
    } else {
        stringResource(id = R.string.text_touch_touch_game_game_over)
    }
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = gameOverScreenTitleMessage,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.text_touch_touch_game_game_over_subheader),
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onClickRestart,
        ) {
            Text(
                text = stringResource(id = R.string.text_touch_touch_game_restart_when_prev_record_not_broken),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }
}

@Preview()
@Composable
fun TouchTouchGameScreenPreview() {
    TouchTouchGameScreen()
}
