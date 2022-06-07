package com.soundscape.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.soundscape.R


/* map, discover, saved, more buttons
    map -> shows main view with current route if there is one
    discover -> shows bars sorted by match and proximity
    saved -> shows your favorites, crawls and events
    more -> shows settings, and so on
*/

@Composable
fun BottomActionBar(
    bottomActionViewModel: BottomActionViewModel,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 10.dp,
        content = {
            BottomNavigationItem(
                modifier = modifier,
                icon = {
                    Column(
                        modifier = modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(painterResource(R.drawable.ic_place), stringResource(R.string.Map))
                        Text(stringResource(R.string.Map))
                    }},
                selectedContentColor= MaterialTheme.colors.primary,
                unselectedContentColor= MaterialTheme.colors.onSurface,
                onClick = { bottomActionViewModel.toggleExclusive("Map") },
                selected = bottomActionViewModel.views["Map"]!!
            )

            BottomNavigationItem(
                modifier = modifier,
                icon = {
                    Column(
                        modifier = modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(painterResource(R.drawable.ic_discover), stringResource(R.string.Discover))
                        Text(stringResource(R.string.Discover))
                    }},
                selectedContentColor= MaterialTheme.colors.primary,
                unselectedContentColor= MaterialTheme.colors.onSurface,
                onClick = { bottomActionViewModel.toggleExclusive("Discover") },
                selected = bottomActionViewModel.views["Discover"]!!
            )

            BottomNavigationItem(
                modifier = modifier,
                icon = {
                    Column(
                        modifier = modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(painterResource(R.drawable.ic_bookmark), stringResource(R.string.Saved))
                        Text(stringResource(R.string.Saved))
                }},
                selectedContentColor= MaterialTheme.colors.primary,
                unselectedContentColor= MaterialTheme.colors.onSurface,
                onClick = { bottomActionViewModel.toggleExclusive("Saved") },
                selected = bottomActionViewModel.views["Saved"]!!
            )

            BottomNavigationItem(
                modifier = modifier,
                icon = {
                    Column(
                        modifier = modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(painterResource(R.drawable.ic_morehoriz), stringResource(R.string.More))
                        Text(stringResource(R.string.More))
                }},
                selectedContentColor= MaterialTheme.colors.primary,
                unselectedContentColor= MaterialTheme.colors.onSurface,
                onClick = { bottomActionViewModel.toggleExclusive("More") },
                selected = bottomActionViewModel.views["More"]!!
            )
        }
    )
}
