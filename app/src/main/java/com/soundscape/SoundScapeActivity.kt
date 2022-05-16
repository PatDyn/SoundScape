package com.soundscape

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplewellness.ui.theme.SoundScapeTheme
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.soundscape.infrastructure.SpotifyConstants
import com.soundscape.infrastructure.findActivity
import com.soundscape.userinterface.LoginBody
import com.soundscape.userinterface.MainBody
import com.soundscape.userinterface.StartOffBody

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoundScapeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) { // TODO: Modify theme to match our specs
                    SoundScapeApp()
                }
            }
        }
    }
}

/*
TODO:
    * Create ViewModel for loading list of bars
    * passing modifiers - for view consistency i gues
    * Functionality to BottomAppBar: Make the buttons show the expected screens
    * Make a floating search bar
        * make it pass things to a function
    * Refactorings:
        * State Hoisting:
            * for bar list
            * map position
            * the search
        *
 */

@Preview(showBackground = true)
@Composable
fun SoundScapePreview(){
    SoundScapeApp()
}

@Composable
fun SoundScapeApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SoundScapeScreen.StartOff.name
    ) {
        composable(SoundScapeScreen.StartOff.name) {
            StartOffBody(
                onClickGoToLoginScreen = {navController.navigate(SoundScapeScreen.Login.name)},
                onClickContinueLocal = {navController.navigate(SoundScapeScreen.Main.name)}
            )
        }

        composable(SoundScapeScreen.Login.name) {
            LoginBody()
        }

        composable(SoundScapeScreen.Main.name) {
            MainBody()
        }

    }
}


