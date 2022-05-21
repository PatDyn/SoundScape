package com.soundscape.userinterface

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.soundscape.R
import com.soundscape.SpotifyActivity
import com.soundscape.infrastructure.getBarList

@Composable
fun LoginWithSpotifyButton() {
    val context = LocalContext.current
    val spotifyIntent = Intent(context, SpotifyActivity::class.java)

    Button(
        onClick = {
            context.startActivity(spotifyIntent)
        })
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
fun GoToLoginScreenButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(stringResource(R.string.Login))
    }
}

@Composable
fun ListBarsButton() {
    var clicked by remember { mutableStateOf(false) }
    if (clicked) {
        val context = LocalContext.current
        val bars = getBarList(context)
        DiscoverCard(bars) }
    else {
        TextButton(onClick = { clicked = true } )
        {
            Text(stringResource(R.string.list_bars))
        }
    }
}

@Composable
fun DiscoverButton(name: String, onClick: () -> Unit, icon : ImageVector ) {
    Box() {
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Row() {
                Icon(imageVector = icon, contentDescription = null)
                Text(name)
            }
        }
    }
}



