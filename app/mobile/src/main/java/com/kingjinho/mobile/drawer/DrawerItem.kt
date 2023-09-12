package com.kingjinho.portfolio.drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.kingjinho.portfolio.R

sealed class DrawerItem {
    val icon: ImageVector
        get() = when (this) {
            is Home -> Icons.Rounded.Home
            is About -> Icons.Rounded.Person
            is Bookmark -> Icons.Rounded.Star
            is Notification -> Icons.Rounded.Notifications
            is Message -> Icons.Rounded.Email
            is Setting -> Icons.Rounded.Settings
            is Help -> Icons.Rounded.Info
            is Logout -> Icons.Rounded.Build
        }

    val title: Int
        get() = when (this) {
            is Home -> R.string.text_drawer_title_home
            is About -> R.string.text_drawer_title_about
            is Bookmark -> R.string.text_drawer_title_bookmark
            is Notification -> R.string.text_drawer_title_notification
            is Message -> R.string.text_drawer_title_message
            is Setting -> R.string.text_drawer_title_setting
            is Help -> R.string.text_drawer_title_help
            is Logout -> R.string.text_drawer_title_logout
        }
    object Home : DrawerItem()
    object About : DrawerItem()
    object Bookmark : DrawerItem()
    object Notification : DrawerItem()
    object Message : DrawerItem()
    object Setting : DrawerItem()
    object Help : DrawerItem()
    object Logout : DrawerItem()
}

val drawerItems = listOf(
    DrawerItem.Home,
    DrawerItem.About,
    DrawerItem.Bookmark,
    DrawerItem.Notification,
    DrawerItem.Message,
    DrawerItem.Setting,
    DrawerItem.Help,
    DrawerItem.Logout,
)