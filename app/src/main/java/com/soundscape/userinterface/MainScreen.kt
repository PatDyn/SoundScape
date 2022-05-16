package com.soundscape.userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soundscape.infrastructure.getBarList

@Preview
@Composable
fun PreviewMainBody() {
    MainBody()
}

@Composable
fun MainBody() {
    Scaffold(
        bottomBar = { BottomActionBar() }
    ) {
        Box {
            Box( // map main view
                Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
            )
            TopSearchBar()

        }
    }
}
