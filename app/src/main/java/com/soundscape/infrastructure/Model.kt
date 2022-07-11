package com.soundscape.infrastructure

import android.content.Context
import com.adamratzman.spotify.auth.SpotifyDefaultCredentialStore
import com.soundscape.SoundScapeApplication

class Model(context: Context) {
    val credentialStore by lazy {
        SpotifyDefaultCredentialStore(
            clientId = SpotifyConstants.CLIENT_ID,
            redirectUri = SpotifyConstants.REDIRECT_URI,
            applicationContext = context
        )
    }

}