package com.soundscape.userinterface

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simplewellness.ui.theme.Grey100
import com.soundscape.userinterface.BottomActionViewModel
import com.example.simplewellness.ui.theme.PurpleAction

/* map, discover, saved, more buttons
    map -> shows main view with current route if there is one
    discover -> shows bars sorted by match and proximity
    saved -> shows your favorites, crawls and events
    more -> shows settings, and so on
*/

@Composable
fun BottomActionBar(
    showStates: BottomActionViewModel
) {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 10.dp,
        content = {
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Home, "Map") },
                selectedContentColor= MaterialTheme.colors.primary,
                unselectedContentColor= MaterialTheme.colors.onSurface,
                onClick = { showStates.toggleExclusive("Map") },
                selected = showStates.views["Map"]!!
            )

            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Place, "Discover") },
                selectedContentColor= MaterialTheme.colors.primary,
                unselectedContentColor= MaterialTheme.colors.onSurface,
                onClick = { showStates.toggleExclusive("Discover") },
                selected = showStates.views["Discover"]!!
            )

            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Star, "Saved") },
                selectedContentColor= MaterialTheme.colors.primary,
                unselectedContentColor= MaterialTheme.colors.onSurface,
                onClick = { showStates.toggleExclusive("Saved") },
                selected = showStates.views["Saved"]!!
            )

            BottomNavigationItem(
                icon = { Icon(Icons.Filled.AccountBox, "More") },
                selectedContentColor= MaterialTheme.colors.primary,
                unselectedContentColor= MaterialTheme.colors.onSurface,
                onClick = { showStates.toggleExclusive("More") },
                selected = showStates.views["More"]!!
            )
        }

    )
}

@Composable
fun TopSearchBar() {
    // floating search bar
    Row(
        Modifier
        .background(MaterialTheme.colors.background)
            .height(48.dp)


    ) {
        Text("Search Bar")
    }
}