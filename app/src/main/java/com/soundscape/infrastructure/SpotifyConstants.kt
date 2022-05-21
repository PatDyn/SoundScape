package com.soundscape.infrastructure

object SpotifyConstants {
    val CLIENT_ID = System.getenv("SPOTIFY_CLIENT_ID") ?: "8129e610de1c4ab19f943e291750d6da"
    val AUTH_TOKEN_REQUEST_CODE = System.getenv("SPOTIFY_AUTH_TOKEN_REQUEST_CODE") ?: 1234
    const val REDIRECT_URI = "soundscape://callback"
}