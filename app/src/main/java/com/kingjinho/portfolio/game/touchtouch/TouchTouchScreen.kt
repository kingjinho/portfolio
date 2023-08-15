package com.kingjinho.portfolio.game.touchtouch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties


@Composable
fun TouchTouchScreen(modifier: Modifier = Modifier) {

    var playerRedHeight by remember {
        mutableStateOf(0.5f)
    }

    Column(
        modifier = modifier.fillMaxHeight()
    ) {
        BoxArea(height = playerRedHeight, player = TouchTouchPlayer.Red) {
            playerRedHeight += 0.07f
        }
        BoxArea(player = TouchTouchPlayer.Blue) {
            playerRedHeight -= 0.07f
        }

        if (playerRedHeight >= 1.0f || playerRedHeight <= 0.0f) {

            Popup(
                alignment = Alignment.Center,
                properties = PopupProperties(focusable = false)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Game Over!!!!")
                    Text(text = if (playerRedHeight >= 1.0f) "Red Win!!!" else "Blue Win!!!")
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

