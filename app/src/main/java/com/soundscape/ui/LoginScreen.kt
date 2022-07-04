package com.soundscape.ui

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer

@Composable
fun LoginBody(
    modifier: Modifier = Modifier,
    onClickContinueLocal: () -> Unit = {},
    activity: Activity? = null
) {
    Row(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
        ) {
            LoginWithSpotifyButton(activity = activity)
        }

    }
}