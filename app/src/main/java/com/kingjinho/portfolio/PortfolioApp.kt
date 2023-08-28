package com.kingjinho.portfolio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kingjinho.portfolio.drawer.ScreenHomeRental
import com.kingjinho.portfolio.game.hopscotch.HopScotchGameScreen
import com.kingjinho.portfolio.game.snake.SnakeGameScreen
import com.kingjinho.portfolio.game.touchtouch.initial.TouchTouchGameScreen
import com.kingjinho.portfolio.game.touchtouch.initial.TouchTouchGameViewModel

@Composable
fun PortfolioApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable("home") {
            HomeScreen(
                onButtonClick = {
                    navController.navigate(it.route)
                }
            )
        }

        composable("curvedSection") {
            CurvedScreen()
        }

        composable(Screen.CustomDrawer.route) {
            ScreenHomeRental()
        }

        composable(Screen.HopScotchGame.route) {
            HopScotchGameScreen {
                navController.popBackStack()
            }
        }

        composable(Screen.TouchTouchGame.route) {
            TouchTouchGameScreen(
                viewModel = TouchTouchGameViewModel()
            )
        }

        composable(Screen.SnakeGame.route) {
            SnakeGameScreen()
        }

    }
}

private val screens = listOf(
    Screen.CustomDrawer,
    Screen.CurvedSection,
    Screen.HopScotchGame,
    Screen.TouchTouchGame,
    Screen.SnakeGame
)

@Composable
fun HomeScreen(onButtonClick: (Screen) -> Unit = {}) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center
    ) {

        screens.forEach {
            NavigationButton(
                modifier = Modifier.padding(bottom = 16.dp),
                item = it,
                onButtonClick = onButtonClick
            )
        }
    }
}

@Composable
fun NavigationButton(
    modifier: Modifier = Modifier,
    onButtonClick: (Screen) -> Unit = {},
    item: Screen
) {
    Button(
        onClick = { onButtonClick(item) },
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(text = stringResource(item.titleRes))
    }

}