package com.soundscape.ui

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adamratzman.spotify.auth.pkce.startSpotifyClientPkceLoginActivity
import com.soundscape.SoundScapeActivity
import com.soundscape.R
import com.soundscape.SpotifyActivity
import com.soundscape.domain.Location
import com.soundscape.pkceClassBackTo
import java.lang.Float.max

@Composable
fun LoginWithSpotifyButton(
    modifier: Modifier = Modifier,
    activity: Activity? = null
) {

    pkceClassBackTo = SoundScapeActivity::class.java

    Button(
        modifier = modifier,
        onClick = {
            activity?.startSpotifyClientPkceLoginActivity(SpotifyActivity::class.java)
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
    Box ()
    {
        IconButton(
            modifier = modifier,
            onClick = onClick
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.Top,
            ) {
                var maxBaseline by remember { mutableStateOf(0f) }
                fun updateMaxBaseline(textLayoutResult: TextLayoutResult) {
                    maxBaseline = max(maxBaseline, textLayoutResult.size.height - textLayoutResult.lastBaseline)
                }
                val topBaselinePadding = with(LocalDensity.current) { maxBaseline.toDp() }
                Icon(
                    modifier = Modifier.padding(bottom = topBaselinePadding),
                    imageVector = icon,
                    contentDescription = null,
                )
                Text(
                    modifier = Modifier.paddingFromBaseline(bottom = topBaselinePadding),
                    text = name,
                    onTextLayout = ::updateMaxBaseline
                )
            }
        }
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


