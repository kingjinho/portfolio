package com.kingjinho.portfolio.drawer

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kingjinho.portfolio.R
import com.kingjinho.portfolio.ui.theme.PortfolioTheme

@Composable
fun ScreenHomeRental() {

    val locations = remember {
        locationList.toMutableStateList()
    }
    var inputText by remember { mutableStateOf("") }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp)
    ) {
        item {
            LocationHeader(
                items = locations,
                onNewLocationSelected = {
                    locations.forEachIndexed { index, locationItem ->
                        locationItem.isSelected = index == it
                    }
                }
            )
            SearchBar(
                input = inputText,
                onInputChanged = { inputText = it },
                modifier = Modifier.padding(top = 24.dp)
            )

            RentListByType(modifier = Modifier.padding(top = 24.dp))
        }
    }
}

@Composable
fun LocationHeader(
    items: List<LocationItem>,
    onNewLocationSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            var isExpanded by remember { mutableStateOf(false) }
            Text(
                text = stringResource(id = R.string.text_location),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF838383)
                ),
            )

            Row(
                modifier = Modifier
                    .clickable { isExpanded = true }
            ) {
                Text(
                    text = items.first { it.isSelected }.name,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000)
                    ),
                )
                Spacer(modifier = modifier.padding(horizontal = 4.dp))
                Image(
                    imageVector = if (isExpanded) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    },
                    contentDescription = stringResource(id = if (isExpanded) R.string.text_location_dropdown_expanded else R.string.text_location_dropdown_not_expanded),
                )

                DropdownMenu(
                    expanded = isExpanded, onDismissRequest = {
                        isExpanded = false
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    items.forEachIndexed { index, location ->
                        DropdownMenuItem(text = {
                            Text(text = location.name)
                        }, onClick = {
                            isExpanded = false
                            onNewLocationSelected(index)
                        })
                    }
                }
            }
        }

        Image(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Outlined.Notifications,
            contentDescription = stringResource(id = R.string.text_notification)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    input: String,
    onInputChanged: (String) -> Unit = {}
) {
    Row(modifier = modifier.padding(horizontal = 20.dp)) {
        TextField(
            value = input, onValueChange = onInputChanged,
            modifier = modifier
                .weight(1f),
            placeholder = {
                Text(text = stringResource(id = R.string.home_rental_search_bar_placeholder))
            },
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            leadingIcon = {
                Image(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = stringResource(id = R.string.btn_search_bar)
                )
            },
        )

        Spacer(modifier = modifier.padding(horizontal = 8.dp))

        Image(
            imageVector = Icons.Outlined.List,
            contentDescription = stringResource(id = R.string.btn_search),
            modifier = modifier.size(56.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RentListByType(modifier: Modifier = Modifier) {
    Column {
        var selectedIndex by remember { mutableStateOf(0) }
        val tabs = listOf(
            R.string.text_first_tab_house,
            R.string.text_first_tab_apartment,
            R.string.text_first_tab_hotel,
            R.string.text_first_tab_villa
        )
        TabRow(
            selectedTabIndex = selectedIndex,
            modifier = modifier.padding(horizontal = 20.dp)
        ) {
            tabs.forEachIndexed { index, stringRes ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                    },
                    modifier = Modifier.height(48.dp)
                ) {
                    Text(
                        text = stringResource(id = stringRes)
                    )
                }
            }
        }
        SectionNearByMe()
        SectionBestDeals()
    }
}

@Composable
fun SectionNearByMe(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(top = 24.dp)
    ) {
        Row(
            modifier = modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.text_title_near_by_me_section),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                ),
                modifier = modifier.weight(1f)
            )
            Spacer(modifier = modifier.padding(vertical = 8.dp))
            Text(
                text = stringResource(R.string.text_see_more),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF858585),
                )
            )
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(2) {
                Surface(
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier
                        .width(222.dp)
                        .height(272.dp)
                ) {
                    GlideImage(
                        data = getRandomImageUrl(),
                        contentDescription = stringResource(
                            id = R.string.text_random_image
                        ),
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .width(222.dp)
                            .height(272.dp)
                    )

                    Image(
                        painter = painterResource(id = getRandomImageRes()),
                        contentDescription = stringResource(
                            id = R.string.text_random_image
                        ),
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .width(222.dp)
                            .height(272.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SectionBestDeals(modifier: Modifier = Modifier) {
    Row {

    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SectionNearByMePreview() {
    PortfolioTheme {
        SectionNearByMe()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SearchBarPreview() {
    PortfolioTheme {
        SearchBar(input = "asdf", onInputChanged = {})
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ScreenHomeRentalPreview() {
    PortfolioTheme {
        ScreenHomeRental()
    }
}