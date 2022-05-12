package com.soundscape

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            // TODO: Add a theme for consistent colouring
            SoundScapeApp()
        }
    }
}


fun spotifyLogin(context: Context) {
    val activity = context.findActivity()
    val builder: AuthorizationRequest.Builder = AuthorizationRequest.Builder(
        SpotifyConstants.CLIENT_ID,
        AuthorizationResponse.Type.TOKEN,
        SpotifyConstants.REDIRECT_URI
    ).setScopes(Array<String>(1){"user-read-private,user-top-read"})
    /* user-read private, for profile pic and name,
    *  user-top-read, gets top tracks (or artists, up to 50) from all time,
    *  last ~6months or ~4weeks.*/

    val request: AuthorizationRequest = builder.build()

    AuthorizationClient.openLoginActivity(
        activity,
        SpotifyConstants.AUTH_TOKEN_REQUEST_CODE as Int,
        request
    )
}

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


