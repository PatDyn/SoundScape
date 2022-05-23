package com.soundscape.userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.soundscape.infrastructure.getBarList

@Composable
fun MainBody(
    modifier: Modifier = Modifier,
    bottomActionViewModel: BottomActionViewModel,
    onClickGoToDetailsScreen: () -> Unit = {}
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
                DiscoverCard(bottomActionViewModel, onClickGoToDetailsScreen)
            }
            if (bottomActionViewModel.views["Saved"] == true) {
                SavedCard()
            }
        }
    }
}
