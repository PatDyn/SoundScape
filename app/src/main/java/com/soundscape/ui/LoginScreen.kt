package com.soundscape.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable

@Composable
fun LoginBody(
    onClickContinueLocal: () -> Unit = {}
) {
    Row() {
        Column() {
            LoginWithSpotifyButton()
        }

    }
}