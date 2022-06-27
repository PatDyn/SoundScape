package com.soundscape

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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


/* ToDo: Implementing PKCE
        need to add state for security
        for pkce add val code_challenge_method = "S256"
        code_challenge = hash(generate.random.string(), SHA256, length = 64)
        (requestCode, state) = result of sending authrequest to spotify api
        val uri = intent?.data
        val response = AuthorizationResponse.fromUri(uri)
        General Info: https://developer.spotify.com/documentation/general/guides/authorization/
        Authorization Code Flow: https://developer.spotify.com/documentation/general/guides/authorization/code-flow/
 */

/* ToDo: Saving the tokens
    using dataStore and dataStoreManager pattern
    https://developer.android.com/topic/libraries/architecture/datastore#kts
    https://levelup.gitconnected.com/modern-data-storage-on-android-meet-jetpack-datastore-part-1-2-9f314c994fc8
    Working Example:
    https://github.com/sunny52525/JetSpotify
 */

/* ToDO: Integrate with JetpackCompose
    Working Example:
    https://github.com/sunny52525/JetSpotify
*/



class SpotifyActivity : Activity() {


    // build user authorization request
    private val builder: AuthorizationRequest.Builder = AuthorizationRequest.Builder(
        SpotifyConstants.CLIENT_ID,
        AuthorizationResponse.Type.TOKEN, // needs to be .CODE
        SpotifyConstants.REDIRECT_URI,
    ).setScopes(Array(1) { "user-read-private,user-top-read" }) // probably delete user-read-private

    /* user-read private, for profile pic and name,
    *  user-top-read, gets top tracks (or artists, up to 50) from all time,
    *  last ~6months or ~4weeks.*/

    public override fun onStart() {

        super.onStart()

        //initial spotify token
        var SPOTIFY_TOKEN: String = ""

        //call to save the access token Permanently //TODO call when token is generated
        fun saveToken(token: String) {
            val saveToken: SharedPreferences =
                getSharedPreferences("USER_SPOTIFY_ACCESS_TOKEN", Context.MODE_PRIVATE)

            with(saveToken.edit()) {
                putString("USER_SPOTIFY_ACCESS_TOKEN", token)
                apply()
            }
        }

        fun loadToken() {
            val savedToken: SharedPreferences =
                getSharedPreferences("USER_SPOTIFY_ACCESS_TOKEN", Context.MODE_PRIVATE)

            SPOTIFY_TOKEN = savedToken.getString("USER_SPOTIFY_ACCESS_TOKEN", "").toString()
        }

        loadToken()
        //TODO if (SPOTIFY_TOKEN == "") bla bla open login activity ( "" is default value)

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