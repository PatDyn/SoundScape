package com.soundscape.infrastructure

import com.adamratzman.spotify.auth.SpotifyDefaultCredentialStore
import com.soundscape.SoundScapeApplication

object Model {
    val credentialStore by lazy {
        SpotifyDefaultCredentialStore(
            clientId = SpotifyConstants.CLIENT_ID,
            redirectUri = SpotifyConstants.REDIRECT_URI,
            applicationContext = SoundScapeApplication.context
        )
    }
}