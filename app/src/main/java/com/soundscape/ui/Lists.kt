package com.soundscape.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LocationList(
    clickedIndex: Int,
    bottomActionViewModel: BottomActionViewModel,
    modifier: Modifier = Modifier,
    onClickGoToDetailScreen: (Int) -> Unit = {}
) {
    val locations = bottomActionViewModel.locations
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        //contentPadding = PaddingValues(16.dp)
    ) {
        items(count = locations.size) { index ->
            LocationSmallCard(
                location = locations[index],
                index = index,
                clickedIndex = clickedIndex,
                modifier = modifier,
                bottomActionViewModel = bottomActionViewModel,
                onClickGoToDetailScreen = onClickGoToDetailScreen
            )
        }
    }
}

@Composable
fun HorizontalList(
    strings: List<String>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.padding(start = 4.dp, end = 4.dp)
    ) {
        items(strings) { genre ->
            Text(
                modifier = modifier.padding(start = 4.dp, end = 4.dp),
                text = genre,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface)
            Divider(
                color = MaterialTheme.colors.onSurface,
                modifier = modifier
                    .height(18.dp)
                    .width(2.dp)
            )
        }
    }
}
