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
    onClickGoToDetailScreen: (Int) -> Unit = {}
) {
    val locations = bottomActionViewModel.locations
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        //contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 25.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Bars",
                    style = MaterialTheme.typography.h3
                )
            }
        }
        items(count = locations.size) { index ->
            LocationSmallCard(
                locations[index],
                index,
                clickedIndex,
                bottomActionViewModel,
                onClickGoToDetailScreen)
        }
    }
}

@Composable
fun HorizontalList(strings: List<String>) {
    LazyRow(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp)
    ) {
        items(strings) { genre ->
            Text(
                modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                text = genre,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface)
            Divider(
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .height(18.dp)
                    .width(2.dp)

            )
        }
    }
}
