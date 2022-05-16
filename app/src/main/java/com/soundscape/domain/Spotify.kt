package com.soundscape.domain

import android.content.Context
import com.soundscape.infrastructure.SpotifyConstants
import com.soundscape.infrastructure.findActivity
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse

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