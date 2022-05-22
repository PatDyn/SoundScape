package com.soundscape.userinterface

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.example.simplewellness.ui.theme.Purple200
import com.soundscape.domain.Location
import java.util.*


@Composable
fun DiscoverCard(
    locations: List<Location>
    ) {
    Card() {
        BarList(locations)
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
fun EventsCard() {}

// hier muss ne bar liste rein
@Composable
fun LocationSmallCard(location: Location) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Box(modifier = Modifier.padding(6.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()) {
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
                        Text(text = location.locationType.capitalize() + " · " + location.distance,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface)
                    }
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
                    LazyRow() {

                        items(location.genres) { genre ->
                            Text(text = genre,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface)
                        }
                    }
                    Row(modifier = Modifier.background(Color.Gray) ) {
                        Text("Here Buttons will appear")
                    }
                }
                /*Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) { // contains match score
                    Text(location.matchScore)
                }*/

            }

        }

    }
}