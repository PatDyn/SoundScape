package com.soundscape.domain

import android.content.Context
import android.content.Intent
import android.media.session.MediaSession
import androidx.browser.trusted.Token
import com.soundscape.infrastructure.SpotifyConstants
import com.soundscape.infrastructure.findActivity
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import kotlin.reflect.typeOf

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

    fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
        onActivityResult(requestCode, resultCode, intent)

        if (requestCode == SpotifyConstants.AUTH_TOKEN_REQUEST_CODE) {
            val response: AuthorizationResponse =
                AuthorizationClient.getResponse(resultCode, intent)

            //if (response is AuthorizationResponse.Type.TOKEN){/*..handle successful login..*/}
        }
    }

}