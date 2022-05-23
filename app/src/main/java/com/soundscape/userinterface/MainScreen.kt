package com.soundscape.userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MainBody(
    modifier: Modifier = Modifier,
    clickedIndex: Int,
    bottomActionViewModel: BottomActionViewModel,
    onClickGoToDetailsScreen: (Int) -> Unit = {}
) {
    Scaffold(
        bottomBar = { BottomActionBar(bottomActionViewModel)
        }
    ) {
        Box {
            Box( // map main view
                Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
            )
            if (bottomActionViewModel.views["Map"]!!) {
                TopSearchBar()
            }
            if (bottomActionViewModel.views["Discover"] == true) {
                DiscoverCard(clickedIndex, bottomActionViewModel, onClickGoToDetailsScreen)
            }
            if (bottomActionViewModel.views["Saved"] == true) {
                SavedCard()
            }
        }
    }
}
