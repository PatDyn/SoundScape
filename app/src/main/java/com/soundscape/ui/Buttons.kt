package com.soundscape.ui

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.soundscape.R
import com.soundscape.SpotifyActivity
import com.soundscape.domain.Location

@Composable
fun LoginWithSpotifyButton(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val spotifyIntent = Intent(context, SpotifyActivity::class.java)

    Button(
        modifier = modifier,
        onClick = {
            context.startActivity(spotifyIntent)
        })
    {
        Text(stringResource(R.string.log_in_with_spotify))
    }
}

@Composable
fun ContinueWithLocalButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        modifier = modifier,
        onClick = onClick
    )
    {
        Text(stringResource(R.string.Continue))
    }
}

@Composable
fun GoToLoginScreenButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(stringResource(R.string.Login))
    }
}

@Composable
fun SavedNavButton(
    name: String,
    onClick: () -> Unit,
    icon : ImageVector,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
        Text(name)
    }
}

@Composable
fun AddFavButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) { // TODO: Extend with onClick and state when necessary
    CustomIconButton(
        width = dimensionResource(R.dimen.logo_button_size_horizontal),
        height = dimensionResource(R.dimen.logo_button_size_vertical),
        onClick = onClick,
        content = {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = stringResource(R.string.add_to_favourites)
            ) },
        modifier = modifier
    )
}

@Composable
fun AddToCrawlButton(
    location: Location,
    modifier: Modifier = Modifier
) {
    CustomIconButton(
        width = dimensionResource(R.dimen.logo_button_size_horizontal),
        height = dimensionResource(R.dimen.logo_button_size_vertical),
        onClick = { /*TODO*/ },
        content = {
            Icon(
                painter = painterResource(R.drawable.logo),
                contentDescription = "${stringResource(id = R.string.add_to_crawl)} + ${location.name}"
            ) },
        modifier = modifier
    )
}

@Composable
fun RouteButton(
    location: Location,
    modifier: Modifier = Modifier
) {
    CustomIconButton(
        width = dimensionResource(R.dimen.logo_button_size_horizontal),
        height = dimensionResource(R.dimen.logo_button_size_vertical),
        onClick = { /*TODO*/ },
        content = {
        Icon(
            imageVector = Icons.Rounded.Place,
            contentDescription = "${stringResource(id = R.string.Route)} + ${location.name}"
        ) },
        modifier = modifier
    )
}

@Composable
fun CustomIconButton(
    width: Dp,
    height: Dp,
    onClick: () -> Unit,
    content: @Composable() () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier.size(width, height),
        onClick = onClick
    ) {
        content()
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


