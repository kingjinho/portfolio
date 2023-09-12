package com.kingjinho.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kingjinho.mobile.ui.theme.PortfolioTheme
import com.kingjinho.portfolio.com.kingjinho.mobile.PortfolioApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortfolioTheme {
                // A surface container using the 'background' color from the theme
                PortfolioApp()
            }
        }
    }
}