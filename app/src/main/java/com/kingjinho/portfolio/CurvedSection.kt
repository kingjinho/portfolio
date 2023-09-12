package com.kingjinho.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kingjinho.portfolio.ui.theme.PortfolioTheme

@Composable
fun CurvedScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xff4C7380))
    ) {
        item {
            FirstSection(
                modifier = modifier.fillMaxWidth()
            )
        }
        item {
            SecondSection(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        }

    }
}

@Composable
fun FirstSection(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(horizontal = 24.dp)
    ) {
        Column {
            val greetingMsg = buildAnnotatedString {
                val greetingPrefix =
                    stringResource(id = R.string.text_screen_curved_section_greeting)
                append(greetingPrefix)
                addStyle(
                    style = SpanStyle(
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight(600),
                        letterSpacing = 0.12.sp
                    ),
                    start = 0,
                    end = greetingPrefix.length
                )
                appendLine()
                val userName = "Kimberly Mastrangelo"
                append(userName)
                addStyle(
                    style = SpanStyle(
                        color = Color(0xff404040),
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        letterSpacing = 0.07.sp
                    ),
                    start = greetingPrefix.length + 1,
                    end = this.length
                )
            }
            Text(text = greetingMsg)
        }
    }
}

@Composable
fun SecondSection(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(Color.White)
    ) {
        Text("aaaaa")
    }
}

@Preview(showBackground = true)
@Composable
fun CurvedScreenPreview() {
    PortfolioTheme {
        CurvedScreen()
    }
}