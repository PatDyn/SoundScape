package com.soundscape.infrastructure

object SpotifyConstants {
    val CLIENT_ID = System.getenv("SPOTIFY_CLIENT_ID") ?: ""
    val AUTH_TOKEN_REQUEST_CODE = System.getenv("SPOTIFY_AUTH_TOKEN_REQUEST_CODE") ?: 1234
    const val REDIRECT_URI = "soundscape://"
}