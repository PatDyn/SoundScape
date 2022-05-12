package com.soundscape.userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.soundscape.R

@Composable
fun StartOffBody(
    onClickContinueLocal: () -> Unit = {},
    onClickGoToLoginScreen: () -> Unit = {}
) {
    // column parent
    Column {

        // row for logo
        Row() {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = stringResource(R.string.LogoContentDescription),
                modifier = Modifier.size(
                    dimensionResource(R.dimen.logo_size_horizontal),
                    dimensionResource(R.dimen.logo_size_vertical)
                )
            )
        }
        // row for search bar description
        Row() {
            Text("Searchbar Description")
        }

        // row for tag search bar
        Row(){
            Text("Searchbar")
        }
        // two columns in row for tags
        Row() {
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
        Column() {
            GoToLoginScreen(onClickGoToLoginScreen)
            ContinueWithLocalButton(onClickContinueLocal)
        }
    }
}
