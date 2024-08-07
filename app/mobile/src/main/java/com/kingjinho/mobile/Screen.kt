package com.kingjinho.mobile

import com.kingjinho.portfolio.R

sealed class Screen(val route: String, val titleRes: Int) {

    object Home : Screen(route = "home", titleRes = R.string.text_screen_title_home)

    object CustomDrawer :
        Screen(route = "customDrawer", titleRes = R.string.text_screen_title_custom_drawer)

    object HopScotchGame : Screen(
        route = "game/hopscotch", titleRes = R.string.text_screen_title_hopscotch_game
    )

    object TouchTouchGame : Screen(
        route = "game/touchtouch",
        titleRes = R.string.text_screen_title_touch_touch_game
    )

    object SnakeGame : Screen(
        route = "game/snake",
        titleRes = R.string.text_screen_title_snake_game
    )

    object CurvedSection : Screen(
        route = "curvedSection",
        titleRes = R.string.text_screen_title_curved_section
    )

    object TicTacToeGame: Screen(
        route = "game/tictactoe",
        titleRes = R.string.text_screen_title_tic_tac_toe_game
    )
}
