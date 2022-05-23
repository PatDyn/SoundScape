package com.soundscape.userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.soundscape.R
import org.intellij.lang.annotations.JdkConstants


/* map, discover, saved, more buttons
    map -> shows main view with current route if there is one
    discover -> shows bars sorted by match and proximity
    saved -> shows your favorites, crawls and events
    more -> shows settings, and so on
*/

@Composable
fun BottomActionBar(
    bottomActionViewModel: BottomActionViewModel
) {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 10.dp,
        content = {
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.ic_place), "Map") },
                selectedContentColor= MaterialTheme.colors.primary,
                unselectedContentColor= MaterialTheme.colors.onSurface,
                onClick = { bottomActionViewModel.toggleExclusive("Map") },
                selected = bottomActionViewModel.views["Map"]!!
            )

            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.ic_discover), "Discover") },
                selectedContentColor= MaterialTheme.colors.primary,
                unselectedContentColor= MaterialTheme.colors.onSurface,
                onClick = { bottomActionViewModel.toggleExclusive("Discover") },
                selected = bottomActionViewModel.views["Discover"]!!
            )

            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.ic_bookmark), "Saved") },
                selectedContentColor= MaterialTheme.colors.primary,
                unselectedContentColor= MaterialTheme.colors.onSurface,
                onClick = { bottomActionViewModel.toggleExclusive("Saved") },
                selected = bottomActionViewModel.views["Saved"]!!
            )

            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.ic_morehoriz), "More") },
                selectedContentColor= MaterialTheme.colors.primary,
                unselectedContentColor= MaterialTheme.colors.onSurface,
                onClick = { bottomActionViewModel.toggleExclusive("More") },
                selected = bottomActionViewModel.views["More"]!!
            )
        }
    )
}

@Composable
fun TopSearchBar() {
    Card() {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .height(64.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(value = "Search for Location, Genres, #tags ...", onValueChange = {})
        }
    }
}