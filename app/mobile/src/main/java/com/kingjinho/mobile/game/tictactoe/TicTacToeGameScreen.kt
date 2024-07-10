package com.kingjinho.mobile.game.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TicTacToeGameScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            items(9) {
                TicTacToeCell()
            }
        }
    }
}

@Composable
fun TicTacToeCell() {
    Box(
        modifier = Modifier
            .aspectRatio(1.0f)
            .background(color = Color.White)
    ) {

    }
}

@Preview
@Composable
private fun TicTacToeGamePreview() {
    TicTacToeGameScreen()
}
