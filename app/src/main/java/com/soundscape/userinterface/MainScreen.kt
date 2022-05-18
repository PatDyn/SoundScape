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

@Preview
@Composable
fun PreviewMainBody() {
    MainBody()
}

@Composable
fun MainBody(
    modifier: Modifier = Modifier,
    bottomActionViewModel: BottomActionViewModel = viewModel()
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
                val context = LocalContext.current
                DiscoverCard(locations = getBarList(context))
            }
            if (bottomActionViewModel.views["Saved"] == true) {
                SavedCard()
            }


        }
    }
}
