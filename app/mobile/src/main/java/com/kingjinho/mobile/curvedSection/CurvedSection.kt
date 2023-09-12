package com.kingjinho.mobile.curvedSection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kingjinho.mobile.ui.theme.PortfolioTheme
import com.kingjinho.portfolio.R

@Composable
fun CurvedSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        FirstSection(
            modifier = Modifier
                .background(
                    color = Color(0xFF4C7380),
                    shape = RoundedCornerShape(bottomStart = 32.dp)
                )
                .padding(
                    top = 16.dp,
                    start = 24.dp,
                    end = 24.dp)
        )

        Box(
            modifier = Modifier.background(
                color = Color(0xFF4C7380),
            )
        ) {
            SecondSection(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(topEnd = 32.dp))
                    .background(Color.White)
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun FirstSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
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

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White, CircleShape)
            ) {
                Image(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .background(Color(0xFFD8E4E8), RoundedCornerShape(28.dp))
                .padding(18.dp)
        ) {
            Column {
                Row {
                    Text(
                        text = "⛅",
                        style = TextStyle(fontSize = 60.sp),
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            "May 16, 2023 10:15 am",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 12.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF404040),
                                letterSpacing = 0.06.sp,
                            )
                        )
                        Text(
                            "Cloudy",
                            style = TextStyle(
                                fontSize = 18.sp,
                                lineHeight = 18.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFF404040),
                                letterSpacing = 0.06.sp,
                            )
                        )
                        Text(
                            "Jakarta, Indonesia",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 12.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF404040),
                                letterSpacing = 0.06.sp,
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(15.5.dp))
                    Text(
                        text = "19°C",
                        style = TextStyle(
                            fontSize = 40.sp,
                            lineHeight = 40.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF404040),
                            letterSpacing = 0.2.sp,
                        )
                    )
                }
                Divider(
                    color = Color(0xFF4C7380)
                )
            }
        }
        Spacer(modifier = Modifier.height(13.dp))
    }
}

@Composable
fun SecondSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text("aaaaa")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CurvedScreenPreview() {
    PortfolioTheme {
        CurvedSection()
    }
}