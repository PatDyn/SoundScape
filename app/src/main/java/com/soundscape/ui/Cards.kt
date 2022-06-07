package com.soundscape.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.List
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
    modifier: Modifier = Modifier,
    onClickGoToDetailScreen: (Int) -> Unit = {}
    ) {
    Card(
        modifier = modifier
    ) {
       LocationList(
           clickedIndex = clickedIndex,
           bottomActionViewModel = bottomActionViewModel,
           onClickGoToDetailScreen = onClickGoToDetailScreen)
    }
}

@Composable
fun SavedCard(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SavedNavButton("Favourites", {}, Icons.Outlined.Star)
            SavedNavButton("My Crawls", {}, Icons.Filled.AccountBox)
            SavedNavButton("Events", {}, Icons.Rounded.List)
        }
    }
}

@Composable
fun FavouritesCard(
    modifier: Modifier = Modifier
) {}

@Composable
fun MyCrawlsCard(
    modifier: Modifier = Modifier
) {}

@Composable
fun EventsCard(
    location: Location,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
        ) {
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
    modifier: Modifier = Modifier,
    onClickGoToDetailScreen: (Int) -> Unit = {},
    ) {
    bottomActionViewModel.location = bottomActionViewModel.locations[clickedIndex]
    Card(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = { onClickGoToDetailScreen.invoke(index) }),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Box(
            modifier = modifier.padding(6.dp)
        ) {
            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = modifier.fillMaxWidth()
                ) { // contains name type distance etc
                    Row(
                        modifier = modifier.fillMaxWidth()
                    ) {
                            Column(
                                modifier = modifier.width(320.dp)
                            ) {
                                Text(
                                    text = location.name,
                                    style = MaterialTheme.typography.h2,
                                    color = MaterialTheme.colors.onBackground,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis)
                            }
                            Spacer(
                                modifier = modifier.weight(1f)
                            )
                            Column(
                                modifier = modifier
                            ) {
                                Text(
                                    text = location.matchScore,
                                    style = MaterialTheme.typography.h2,
                                    color = MaterialTheme.colors.onBackground
                                )
                            }
                    }
                    Row(
                        modifier = modifier
                    ) {
                        Text(
                            text = location.locationType.replaceFirstChar { it.uppercase() } + " · " + location.distance,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                    BarOpeningInfo(location)
                    HorizontalList(location.genres)
                    Row(
                        modifier = modifier.background(Color.Gray)
                    ) {
                        Text("Here Buttons will appear")
                    }
                }
            }
        }
    }
}

@Composable
fun LocationDescriptionCard(
    location: Location,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
        ) {
            Text(text = location.description)
        }
    }
}

@Composable
fun BarOpeningInfo(
    location: Location,
    modifier: Modifier = Modifier
) {
    if (location.isOpen) {
        Row(
            modifier = modifier
        ) {
            Text(
                text = "${stringResource(R.string.open)}",
                style = MaterialTheme.typography.body1,
                color = Color.Green
            )
            Text(text = " · ${stringResource(R.string.closes)} " + location.getClosingTime(),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface)
        }
    } else {
        Row(
            modifier = modifier
        ) {
            Text(
                text = "${stringResource(R.string.closed)}",
                style = MaterialTheme.typography.body1,
                color = Color.Red
            )
            Text(
                text = " · ${stringResource(R.string.opens)} " + location.getOpeningTime(),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface)
        }
    }
}

@Composable
fun TopSearchBarCard(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable { mutableStateOf("") }
    var activeSearchbar by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
        ) {
            Row(
                modifier = modifier
                    .background(MaterialTheme.colors.background)
                    .height(64.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    placeholder = {
                        Text("Search for Location, Events, #Genres ...")
                        activeSearchbar = false
                    },
                    value = text,
                    onValueChange = {
                        text = it
                        activeSearchbar = true
                    },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = ""
                        )
                    },
                    keyboardActions = KeyboardActions {
                        onSearchClick()
                    },
                )
            }
            if (activeSearchbar) {
                Row(
                    modifier = modifier
                ) {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "All")
                    }
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Locations")
                    }
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Events")
                    }
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "#Genres")
                    }
                }
            }
        }
    }
}
