package com.soundscape.userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.soundscape.R
import com.soundscape.domain.Bar
import com.soundscape.infrastructure.getBarList

@Composable
fun BottomActionBar() {
    /* map, discover, saved, more buttons
        map -> shows main view with current route if there is one
        discover -> shows bars sorted by match and proximity
        saved -> shows your favorites, crawls and events
        more -> shows settings, and so on
 */
    BottomAppBar(
        elevation = 10.dp,
        content = {
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Home, "Map") },
                selectedContentColor= Color.White,
                unselectedContentColor= Color.White.copy(alpha = 0.4f),
                onClick = {},
                selected = false
            )
            val context = LocalContext.current
            var showBars = false
            val bars = getBarList(context)

            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Add, "Discover") },
                selectedContentColor= Color.White,
                unselectedContentColor= Color.White.copy(alpha = 0.4f),
                onClick = { showBars = true },
                selected = showBars
            )

            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Star, "Saved") },
                selectedContentColor= Color.White,
                unselectedContentColor= Color.White.copy(alpha = 0.4f),
                onClick = {},
                selected = false
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.AccountBox, "More") },
                selectedContentColor= Color.White,
                unselectedContentColor= Color.White.copy(alpha = 0.4f),
                onClick = {},
                selected = false
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