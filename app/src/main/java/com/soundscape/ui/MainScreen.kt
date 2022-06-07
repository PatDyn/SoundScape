package com.soundscape.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MainBody(
    clickedIndex: Int,
    viewModel: BottomActionViewModel,
    modifier: Modifier = Modifier,
    onClickGoToDetailsScreen: (Int) -> Unit = {},
) {
    Scaffold(
        bottomBar = { BottomActionBar(viewModel) }
    ) {
        Box {
            Box( // map main view
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.Gray)
            )
            if (viewModel.views["Map"]!!) {
                TopSearchBarCard(
                    {},
                    modifier = modifier
                )
            }
            if (viewModel.views["Discover"] == true) {
                DiscoverCard(
                    clickedIndex = clickedIndex,
                    bottomActionViewModel = viewModel,
                    modifier = modifier,
                    onClickGoToDetailScreen = onClickGoToDetailsScreen
                )
            }
            if (viewModel.views["Saved"] == true) {
                SavedCard(
                    modifier = modifier
                )
            }
        }
    }
}
