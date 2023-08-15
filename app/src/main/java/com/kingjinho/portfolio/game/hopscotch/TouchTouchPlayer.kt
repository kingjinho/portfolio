package com.kingjinho.portfolio.game.hopscotch

import androidx.compose.ui.graphics.Color

sealed class TouchTouchPlayer {
    val color: Color
        get() = when (this) {
            is Red -> Color.Red
            is Blue -> Color.Blue
        }
    object Red : TouchTouchPlayer()
    object Blue : TouchTouchPlayer()
}
