package com.kingjinho.portfolio.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CustomDrawer(modifier: Modifier = Modifier) {
    var isClicked by rememberSaveable {
        mutableStateOf(false)
    }
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        if (isClicked) {
            Drawer1()
        }

        Box(
            modifier = Modifier
                .fillMaxSize(
                    if (isClicked) {
                        0.4f
                    } else {
                        1f
                    }
                )
                .align(Alignment.CenterEnd)
        ) {
            Column(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { isClicked = !isClicked },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Drawer")
                Text(text = "Content")
                Text(text = "Drawer")
                Text(text = "Content")
            }
        }
    }
}

@Composable
fun Drawer1() {
    Surface(
        color = Color.LightGray,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            repeat(4) {
                Text(text = "Item $it")
            }
        }
    }
}