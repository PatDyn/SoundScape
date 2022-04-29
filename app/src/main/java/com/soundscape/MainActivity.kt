package com.soundscape

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.soundscape.infrastructure.SpotifyConstants

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spotify_login_btn = findViewById<Button>(R.id.spotify_login_btn)

        spotify_login_btn.setOnClickListener {
            val builder: AuthorizationRequest.Builder = AuthorizationRequest.Builder(
                SpotifyConstants.CLIENT_ID,
                AuthorizationResponse.Type.TOKEN,
                SpotifyConstants.REDIRECT_URI)

            val request: AuthorizationRequest = builder.build()

            AuthorizationClient.openLoginActivity(
                this,
                SpotifyConstants.AUTH_TOKEN_REQUEST_CODE,
                request
            )
        }
    }
}