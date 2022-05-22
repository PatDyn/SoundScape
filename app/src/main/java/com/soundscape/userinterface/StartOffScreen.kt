package com.soundscape.userinterface

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.soundscape.R

@Composable
fun StartOffBody(
    onClickContinueLocal: () -> Unit = {},
    onClickGoToLoginScreen: () -> Unit = {}
) {
    // column parent
    Column {

        // row for logo
        Row(horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(R.drawable.ic_logoalt),
                contentDescription = stringResource(R.string.LogoContentDescription),
                modifier = Modifier.size(
                    dimensionResource(R.dimen.logo_size_horizontal),
                    dimensionResource(R.dimen.logo_size_vertical)

                )
            )
        }
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "SoundScape",

                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.primary,
                fontSize = 42.sp
            )
        }
        // row for search bar description
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "set your music preferences",
                style = MaterialTheme.typography.h2)
        }

        // row for tag search bar
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()){
            Text("Searchbar")
        }
        // two columns in row for tags
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()) {
            Column() {
                Text("Tags")
                Text("Tags")
            }
            Column() {
                Text("Tags")
                Text("Tags")
            }
        }
        // column for continue, login
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()) {
            Column() {
                GoToLoginScreenButton(onClickGoToLoginScreen)
                ContinueWithLocalButton(onClickContinueLocal)
            }
        }
    }
}
