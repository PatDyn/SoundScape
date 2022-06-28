package com.soundscape.infrastructure

import com.spotify.sdk.android.auth.AuthorizationResponse

object SpotifyConstants {

    const val AUTH_TOKEN_REQUEST_CODE : Int =  1234
    const val spotifyTokenURL : String = "https://accounts.spotify.com/api/token"

    // TODO: we need to protect the client ID
    // Auth Code Flow
    val CLIENT_ID = System.getenv("SPOTIFY_CLIENT_ID") ?: "8129e610de1c4ab19f943e291750d6da"
    val RESPONSE_TYPE = AuthorizationResponse.Type.CODE
    //val RESPONSE_TYPE = "code"
    const val REDIRECT_URI = "soundscape://callback"
    val STATE = getRandomString(16)

    // PKCE
    const val CODE_CHALLENGE_METHOD = "S256"
    val CODE_VERIFIER = getRandomString(64)

}


