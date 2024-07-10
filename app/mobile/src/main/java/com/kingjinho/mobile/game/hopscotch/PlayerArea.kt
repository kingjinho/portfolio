package com.kingjinho.mobile.game.hopscotch

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ColumnScope.PlayerArea(
    modifier: Modifier = Modifier,
    height: Float = 1.0f,
    player: HopScotchPlayer,
    onTouch: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .weight(height)
            .background(player.color)
            .clickable {
                onTouch()
            }
    )
}
