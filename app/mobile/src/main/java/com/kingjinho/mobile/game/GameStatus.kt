package com.kingjinho.mobile.game

sealed class GameStatus {
    object Ready : GameStatus()
    object Playing : GameStatus()
    object GameOver : GameStatus()
}
