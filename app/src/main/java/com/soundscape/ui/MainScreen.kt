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
    modifier: Modifier = Modifier,
    clickedIndex: Int,
    viewModel: BottomActionViewModel,
    onClickGoToDetailsScreen: (Int) -> Unit = {},
) {
    Scaffold(
        bottomBar = { BottomActionBar(viewModel) }
    ) {
        Box {
            Box( // map main view
                Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
            )
            if (viewModel.views["Map"]!!) {
                TopSearchBarCard(
                    {}
                )
            }
            if (viewModel.views["Discover"] == true) {
                DiscoverCard(
                    clickedIndex,
                    viewModel,
                    onClickGoToDetailsScreen
                )
            }
            if (viewModel.views["Saved"] == true) {
                SavedCard()
            }
        }
    }
}
