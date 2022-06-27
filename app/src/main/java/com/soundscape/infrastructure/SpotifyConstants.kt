package com.soundscape.infrastructure

import com.spotify.sdk.android.auth.AuthorizationResponse

object SpotifyConstants {

    val AUTH_TOKEN_REQUEST_CODE = System.getenv("SPOTIFY_AUTH_TOKEN_REQUEST_CODE") ?: 1234

    // TODO: we need to protect the client ID
    // Auth Code Flow
    val CLIENT_ID = System.getenv("SPOTIFY_CLIENT_ID") ?: "8129e610de1c4ab19f943e291750d6da"
    val RESPONSE_TYPE = AuthorizationResponse.Type.CODE
    const val REDIRECT_URI = "soundscape://callback"
    val STATE = getRandomString(16).toHashed("SHA-256")

    // PKCE
    const val CODE_CHALLENGE_METHOD = "S256"
    val CODE_CHALLENGE = getRandomString(64).toHashed("SHA-256")
}


