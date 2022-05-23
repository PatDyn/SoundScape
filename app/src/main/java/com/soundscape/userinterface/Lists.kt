package com.soundscape.userinterface

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.soundscape.domain.Location

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
fun HorizontalTagList(location: Location) { // TODO: Add state once necessary
    val stylesEven = location.musicalStyles.filterIndexed() { index, _ -> index.mod(2) == 0 }
    val stylesOdd = location.musicalStyles.filterIndexed() { index, _ -> index.mod(2) != 0 }

    LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(stylesEven) {
                    musicalStyle -> Text(musicalStyle)
            }

            items(stylesOdd) {
                    musicalStyle -> Text(musicalStyle)
            }
    }
}