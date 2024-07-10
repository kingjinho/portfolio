package com.kingjinho.mobile.game.hopscotch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties

private const val MAXIMUM_HEIGHT_WEIGHT = 1.0f
private const val MINIMUM_HEIGHT_WEIGHT = 0.0f

@Composable
fun HopScotchGameScreen(
    modifier: Modifier = Modifier,
    onBackPressedWhenPopupIsOpen: () -> Unit = {}
) {
    var playerRedHeight by remember {
        mutableFloatStateOf(0.5f)
    }

    var isBackButtonPressed by remember {
        mutableStateOf(false)
    }

    Column {

        if (playerRedHeight > MINIMUM_HEIGHT_WEIGHT) {
            PlayerArea(
                height = playerRedHeight,
                player = HopScotchPlayer.Red
            ) {
                playerRedHeight = (playerRedHeight + 0.07f)
                    .coerceAtMost(MAXIMUM_HEIGHT_WEIGHT)
            }
        }

        if (1.0f - playerRedHeight > MINIMUM_HEIGHT_WEIGHT) {
            PlayerArea(
                height = 1.0f - playerRedHeight,
                player = HopScotchPlayer.Blue
            ) {
                playerRedHeight = (playerRedHeight - 0.07f)
                    .coerceAtLeast(MINIMUM_HEIGHT_WEIGHT)
            }
        }

        if (!isBackButtonPressed && isGameOver(playerRedHeight)) {
            Popup(
                alignment = Alignment.Center,
                properties = PopupProperties(focusable = true),
                onDismissRequest = {
                    isBackButtonPressed = true
                    onBackPressedWhenPopupIsOpen()
                }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.background(Color.White)
                ) {
                    Text(text = "Game Over!!!!")
                    Text(text = if (playerRedHeight == MAXIMUM_HEIGHT_WEIGHT) "Red Win!!!" else "Blue Win!!!")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        playerRedHeight = 0.5f
                    }) {
                        Text(text = "Restart")
                    }
                }
            }
        }
    }
}

private fun isGameOver(playerRedHeight: Float): Boolean {
    return playerRedHeight == MAXIMUM_HEIGHT_WEIGHT || playerRedHeight == MINIMUM_HEIGHT_WEIGHT
}

@Preview
@Composable
fun HopScotchGameScreenPreview() {
    HopScotchGameScreen()
}
