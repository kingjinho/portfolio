package com.kingjinho.portfolio.game.hopscotch

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BoxArea(
    modifier: Modifier = Modifier,
    height: Float = 1.0f,
    player: TouchTouchPlayer,
    onTouch: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(height)
            .background(player.color)
            .clickable {
                onTouch()
            }
    )
}