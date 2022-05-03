package com.soundscape.userinterface

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.soundscape.R
import com.soundscape.spotifyLogin
import com.soundscape.infrastructure.getBarList

@Composable
fun LoginWithSpotifyButton() {
    val context = LocalContext.current
    Button(
        onClick = { spotifyLogin(context) })
    {
        Text(stringResource(R.string.log_in_with_spotify))
    }
}

@Composable
fun ContinueWithLocalButton(onClick: () -> Unit) {
    TextButton(onClick = onClick)
    {
        Text(stringResource(R.string.Continue))
    }
}


@Composable
fun ListBarsButton() {
    var clicked by remember { mutableStateOf(false) }
    if (clicked) {
        val context = LocalContext.current
        val bars = getBarList(context)
        BarDisplay(bars) }
    else {
        TextButton(onClick = { clicked = true } )
        {
            Text(stringResource(R.string.list_bars))
        }
    }
}



