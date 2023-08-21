package com.kingjinho.portfolio.game

sealed class GameStatus {
    object Ready : GameStatus()
    object Playing : GameStatus()
    object GameOver : GameStatus()
}
