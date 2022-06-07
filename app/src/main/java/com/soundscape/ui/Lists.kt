package com.soundscape.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp
import java.lang.Float

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
    var maxBaseline by remember { mutableStateOf(0f) }
    fun updateMaxBaseline(textLayoutResult: TextLayoutResult) {
        maxBaseline =
            Float.max(maxBaseline, textLayoutResult.size.height - textLayoutResult.lastBaseline)
    }
    val topBaselinePadding = with(LocalDensity.current) { maxBaseline.toDp() }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(strings) { genre ->
            Text(
                modifier = modifier.padding(bottom = topBaselinePadding),
                text = genre,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                onTextLayout = ::updateMaxBaseline
            )
        }
    }
}
