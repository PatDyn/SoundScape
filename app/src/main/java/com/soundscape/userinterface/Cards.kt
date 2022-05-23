package com.soundscape.userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.soundscape.R
import com.soundscape.domain.Location


@Composable
fun DiscoverCard(
    clickedIndex: Int,
    bottomActionViewModel: BottomActionViewModel,
    onClickGoToDetailScreen: (Int) -> Unit = {}
    ) {
    Card() {
       LocationList(clickedIndex, bottomActionViewModel, onClickGoToDetailScreen)
    }
}

@Composable
fun SavedCard() {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DiscoverButton("Favourites", {}, Icons.Outlined.Star)
            DiscoverButton("My Crawls", {}, Icons.Filled.AccountBox)
            DiscoverButton("Events", {}, Icons.Rounded.List)
        }
    }
}

@Composable
fun FavouritesCard() {}

@Composable
fun MyCrawlsCard() {}

@Composable
fun EventsCard(location: Location) {
    Card() {
        Column() {
            Text(text = stringResource(R.string.location_upcoming_events))
            HorizontalList(strings = location.events)
        }
    }
}

@Composable
fun LocationSmallCard(
    location: Location,
    index: Int,
    clickedIndex: Int,
    bottomActionViewModel: BottomActionViewModel,
    onClickGoToDetailScreen: (Int) -> Unit = {}
    ) {
    bottomActionViewModel.location = bottomActionViewModel.locations[clickedIndex]
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = { onClickGoToDetailScreen.invoke(index) }),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Box(modifier = Modifier.padding(6.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.fillMaxWidth()) { // contains name type distance etc
                    Row(modifier = Modifier.fillMaxWidth()
                    ) {
                            Column(modifier = Modifier.width(320.dp)) {
                                Text(text = location.name,
                                    style = MaterialTheme.typography.h2,
                                    color = MaterialTheme.colors.onBackground,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis)
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Column() {
                                Text(text = location.matchScore,
                                    style = MaterialTheme.typography.h2,
                                    color = MaterialTheme.colors.onBackground,)
                            }
                    }
                    Row() {
                        Text(text = location.locationType.replaceFirstChar { it.uppercase() } + " · " + location.distance,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface)
                    }
                    BarOpeningInfo(location)
                    HorizontalList(location.genres)
                    Row(modifier = Modifier.background(Color.Gray) ) {
                        Text("Here Buttons will appear")
                    }
                }
            }
        }
    }
}

@Composable
fun LocationDescriptionCard(location: Location) {
    Card() {
        Column() {
            Text(text = location.description)
        }
    }
}

@Composable
fun BarOpeningInfo(location: Location) {
    Row() {
        if (location.isOpen) {
            Text(text = "open",
                style = MaterialTheme.typography.body1,
                color = Color.Green
            )
        } else {
            Text(text = "closed",
                style = MaterialTheme.typography.body1,
                color = Color.Red
            )
        }
        Text(text = " · " + location.getClosingTime(),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface)
    }
}
