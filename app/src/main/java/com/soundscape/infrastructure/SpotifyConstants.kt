package com.soundscape.infrastructure


import java.security.MessageDigest

fun getRandomString(length: Int) : String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}

fun String.toHashed(type: String): String {
    return MessageDigest
        .getInstance(type)
        .digest(this.toByteArray())
        .fold("") { str, it -> str + "%02x".format(it) }
}

object SpotifyConstants {

    // TODO: we need to protect the client ID
    val AUTH_TOKEN_REQUEST_CODE = System.getenv("SPOTIFY_AUTH_TOKEN_REQUEST_CODE") ?: 1234

    val CLIENT_ID = System.getenv("SPOTIFY_CLIENT_ID") ?: "8129e610de1c4ab19f943e291750d6da"
    val RESPONSE_TYPE = "code"
    const val REDIRECT_URI = "soundscape://callback"
    val STATE = getRandomString(16).toHashed("SHA-256")
}


