package com.soundscape.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer

@Composable
fun LoginBody(
    onClickContinueLocal: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
        ) {
            LoginWithSpotifyButton()
        }

    }
}