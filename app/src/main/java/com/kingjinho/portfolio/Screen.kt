package com.kingjinho.portfolio

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
}
