package com.kingjinho.mobile.game.hopscotch

import androidx.compose.ui.graphics.Color

sealed class HopScotchPlayer {
    val color: Color
        get() = when (this) {
            is Red -> Color.Red
            is Blue -> Color.Blue
        }
    object Red : HopScotchPlayer()
    object Blue : HopScotchPlayer()
}
