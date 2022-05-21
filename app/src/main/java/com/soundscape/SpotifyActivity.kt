package com.soundscape

import android.app.Activity
import android.content.Intent
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import com.soundscape.infrastructure.SpotifyConstants
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import se.michaelthelin.spotify.SpotifyApi
import se.michaelthelin.spotify.model_objects.specification.Paging
import se.michaelthelin.spotify.model_objects.specification.Track
import se.michaelthelin.spotify.model_objects.specification.User
import java.lang.Exception


class SpotifyActivity: Activity() {
    private val builder: AuthorizationRequest.Builder = AuthorizationRequest.Builder(
        SpotifyConstants.CLIENT_ID,
        AuthorizationResponse.Type.TOKEN,
        SpotifyConstants.REDIRECT_URI
    ).setScopes(Array(1){"user-read-private,user-top-read"})
    /* user-read private, for profile pic and name,
    *  user-top-read, gets top tracks (or artists, up to 50) from all time,
    *  last ~6months or ~4weeks.*/

    public override fun onStart() {
        super.onStart()

        val policy = ThreadPolicy.Builder().permitNetwork().build()
        StrictMode.setThreadPolicy(policy)
        val request: AuthorizationRequest = builder.build()
        AuthorizationClient.openLoginInBrowser(this, request)
        /*AuthorizationClient.openLoginActivity(
            this,
            AUTH_TOKEN_REQUEST_CODE as Int,
            request
        )*/
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent);
        val uri = intent?.data

        val response = AuthorizationResponse.fromUri(uri)

        when (response.type) {
            AuthorizationResponse.Type.TOKEN -> {
                val spotifyApi = SpotifyApi.Builder()
                    .setAccessToken(response.accessToken)
                    .build()

                // TODO: Make this async
                val topTracksRequest = spotifyApi.usersTopTracks.build()
                try {
                    val tracks: Paging<Track> = topTracksRequest.execute()
                    intent?.putExtra("tracks", tracks)
                } catch (e: Exception) {
                    println(e.message)
                }

                val userProfileRequest = spotifyApi.currentUsersProfile.build()
                try {
                    val user: User = userProfileRequest.execute()
                    intent?.putExtra("user", user);
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            AuthorizationResponse.Type.ERROR -> {}
            else -> {}
        }
        finish()
    }
}