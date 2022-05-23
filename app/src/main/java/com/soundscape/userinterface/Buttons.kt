package com.soundscape.userinterface

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Place
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.soundscape.R
import com.soundscape.SpotifyActivity
import com.soundscape.domain.Location
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

@Composable
fun AddFavButton(onClick: () -> Unit) { // TODO: Extend with onClick and state when necessary
    Box() {
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Row() {
                Icon(imageVector = Icons.Outlined.Star,
                    contentDescription = stringResource(R.string.add_to_favourites))
            }
        }
    }
}

@Composable
fun AddToCrawlButton(location: Location) {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(painter = painterResource(R.drawable.logo),
            contentDescription = "${stringResource(id = R.string.add_to_crawl)} + ${location.name}"
        )
    }
}

@Composable
fun RouteButton(location: Location) {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(imageVector = Icons.Rounded.Place,
            contentDescription = "${stringResource(id = R.string.Route)} + ${location.name}"
        )        
    }
}


/*
@Composable
fun ListBarsButton() {
    var clicked by remember { mutableStateOf(false) }
    if (clicked) {
        DiscoverCard(bars) }
    else {
        TextButton(onClick = { clicked = true } )
        {
            Text(stringResource(R.string.list_bars))
        }
    }
}

 */


