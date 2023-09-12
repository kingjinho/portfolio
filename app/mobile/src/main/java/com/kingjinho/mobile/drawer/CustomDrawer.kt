package com.kingjinho.portfolio.drawer

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kingjinho.portfolio.R

@Composable
fun CustomDrawer(
    modifier: Modifier = Modifier,
    drawerContent: @Composable () -> Unit,
    drawerColor: Color = Color(0xFF0A8ED9),
    bodyContent: @Composable () -> Unit
) {
    var isClicked by rememberSaveable {
        mutableStateOf(false)
    }
    val contentMaxHeight = if (isClicked) {
        0.8f
    } else {
        1f
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Surface(
            color = drawerColor,
            modifier = modifier.matchParentSize()
        ) {
            if (isClicked) {
                drawerContent()
            }
        }

        Box(
            modifier = modifier
                .animateContentSize()
                .fillMaxHeight(contentMaxHeight)
                .fillMaxWidth()
                .align(Alignment.CenterEnd)
                .then(
                    if (isClicked) {
                        modifier.layout { measurable, constraints ->
                            val placeable = measurable.measure(constraints)
                            layout(placeable.width, placeable.height) {
                                placeable.placeRelative(600, 0)
                            }
                        }
                    } else {
                        modifier
                    }
                )) {
            Box(
                modifier = modifier
                    .matchParentSize()
                    .background(
                        Color.White,
                        shape = if (isClicked) MaterialTheme.shapes.large else RectangleShape
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null, onClick = { isClicked = !isClicked })
            ) {
                bodyContent()
                if (isClicked) {
                    Surface(
                        modifier = Modifier.matchParentSize(),
                        color = Color.Transparent
                    ){}
                }
            }
        }
    }
}

@Composable
fun Drawer(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .padding(top = 96.dp),
        verticalArrangement = Arrangement.spacedBy(35.dp)
    ) {
        itemsIndexed(drawerItems) { index, item ->
            DrawerItem(item = item)
            if (index == 2 || index == 5) {
                Spacer(modifier = modifier.padding(bottom = 35.dp))
                Divider(
                    color = Color.White,
                    modifier = modifier
                        .fillMaxWidth(0.45f)
                        .padding(start = 4.dp)
                )
            }
        }
    }
}

@Composable
fun DrawerItem(modifier: Modifier = Modifier, item: DrawerItem) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp)
    ) {
        Icon(
            imageVector = item.icon,
            tint = Color.White,
            contentDescription = stringResource(
                id = R.string.text_drawer_description,
                stringResource(id = item.title)
            )
        )
        Spacer(modifier = modifier.padding(start = 10.dp))
        Text(
            text = stringResource(id = item.title),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF),
            )
        )
    }
}