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
import androidx.compose.ui.unit.dp
import com.example.simplewellness.ui.theme.Purple200
import com.soundscape.domain.Location




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
        Row(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.surface),
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
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Box() {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Column() { // contains name type distance etc
                    Text(location.name)
                    Row() {
                        Text(location.locationType)
                        Text(location.distance)
                    }
                    Row() {
                        if (location.isOpen) {
                            Text(text = "open",
                                color = Color.Green
                            )
                        } else {
                            Text(text = "closed",
                                color = Color.Red
                            )
                        }
                        Text(location.getClosingTime())
                    }
                    LazyRow() {

                        items(location.genres) { genre ->
                            Text(genre)
                        }
                    }
                    Row(modifier = Modifier.background(Color.Gray) ) {
                        Text("Here Buttons will appear")
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) { // contains match score
                    Text(location.matchScore)
                }

            }

        }
        Text(text = location.name)
    }
}