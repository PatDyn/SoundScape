package com.soundscape.userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.soundscape.userinterface.BottomActionViewModel

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
        elevation = 10.dp,
        content = {
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Home, "Map") },
                selectedContentColor= Color.White,
                unselectedContentColor= Color.White.copy(alpha = 0.4f),
                onClick = { showStates.toggleExclusive("Map") },
                selected = showStates.views["Map"]!!
            )

            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Place, "Discover") },
                selectedContentColor= Color.White,
                unselectedContentColor= Color.White.copy(alpha = 0.4f),
                onClick = { showStates.toggleExclusive("Discover") },
                selected = showStates.views["Discover"]!!
            )

            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Star, "Saved") },
                selectedContentColor= Color.White,
                unselectedContentColor= Color.White.copy(alpha = 0.4f),
                onClick = { showStates.toggleExclusive("Saved") },
                selected = showStates.views["Saved"]!!
            )

            BottomNavigationItem(
                icon = { Icon(Icons.Filled.AccountBox, "More") },
                selectedContentColor= Color.White,
                unselectedContentColor= Color.White.copy(alpha = 0.4f),
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
        .background(Color.DarkGray)
    ) {
        Text("Search Bar")
    }
}