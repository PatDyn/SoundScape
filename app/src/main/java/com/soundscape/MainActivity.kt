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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.soundscape.infrastructure.SpotifyConstants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // TODO: Add a theme for consistent colouring
            Surface(color = MaterialTheme.colors.background) {}

        }
    }
}


fun Context.findActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
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
        SpotifyConstants.AUTH_TOKEN_REQUEST_CODE,
        request
    )
}

@Preview(showBackground = true)
@Composable
fun SoundScapePreview(){
    Surface()
}

@Composable
fun Surface() {
    LoginWithSpotifyButton()
    ContinueWithLocalButton()
}

@Composable
fun LoginWithSpotifyButton() {
    val context = LocalContext.current
    Button(
        onClick = { spotifyLogin(context) })
    {
        Text("Login With Spotify")
    }
}

@Composable
fun ContinueWithLocalButton() {
    Button(onClick = { /*TODO*/ })
    {
        Text(text = "Continue")
    }
}

