package com.soundscape.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.soundscape.R

@Composable
fun StartOffBody(
    modifier: Modifier = Modifier,
    onClickContinueLocal: () -> Unit = {},
    onClickGoToLoginScreen: () -> Unit = {}
) {
    // column parent
    Column {
        // row for logo
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.ic_logoalt),
                contentDescription = stringResource(R.string.LogoContentDescription),
                modifier = modifier.size(
                    dimensionResource(R.dimen.logo_size_horizontal),
                    dimensionResource(R.dimen.logo_size_vertical)

                )
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = "SoundScape",
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.primary,
                fontSize = 42.sp
            )
        }
        // row for search bar description
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = "set your music preferences",
                style = MaterialTheme.typography.h2)
        }

        // row for tag search bar
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxWidth()
        ){
            Text("Searchbar")
        }
        // two columns in row for tags
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            Column() {
                Text("#Genres")
                Text("#Genres")
            }
            Column() {
                Text("#Genres")
                Text("#Genres")
            }
        }
        // column for continue, login
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            Column() {
                GoToLoginScreenButton(onClickGoToLoginScreen)
                ContinueWithLocalButton(onClickContinueLocal)
            }
        }
    }
}
