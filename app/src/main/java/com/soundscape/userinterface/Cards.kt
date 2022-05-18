package com.soundscape.userinterface

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.simplewellness.ui.theme.PurpleAction
import com.example.simplewellness.ui.theme.Grey300
import com.soundscape.domain.Bar



@Composable
fun DiscoverCard(
    bars: List<Bar>
    ) {
    Card() {
        BarList(bars)
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

