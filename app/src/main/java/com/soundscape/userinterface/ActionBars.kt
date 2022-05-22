package com.soundscape.userinterface

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simplewellness.ui.theme.Grey100
import com.soundscape.userinterface.BottomActionViewModel
import com.example.simplewellness.ui.theme.PurpleAction
import com.soundscape.R


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
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 10.dp,
        content = {
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.ic_place), "Map") },
                selectedContentColor= MaterialTheme.colors.primary,
                unselectedContentColor= MaterialTheme.colors.onSurface,
                onClick = { showStates.toggleExclusive("Map") },
                selected = showStates.views["Map"]!!
            )

            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.ic_discover), "Discover") },

                selectedContentColor= MaterialTheme.colors.primary,
                unselectedContentColor= MaterialTheme.colors.onSurface,
                onClick = { showStates.toggleExclusive("Discover") },
                selected = showStates.views["Discover"]!!
            )

            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.ic_bookmark), "Saved") },
                selectedContentColor= MaterialTheme.colors.primary,
                unselectedContentColor= MaterialTheme.colors.onSurface,
                onClick = { showStates.toggleExclusive("Saved") },
                selected = showStates.views["Saved"]!!
            )

            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.ic_morehoriz), "More") },
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
            .fillMaxWidth()



    ) {
        Text("Search Bar")
    }
}