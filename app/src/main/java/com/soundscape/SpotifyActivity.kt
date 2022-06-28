package com.soundscape

import android.app.Activity
import android.content.Intent
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Toast
import com.soundscape.infrastructure.SpotifyConstants
import com.soundscape.infrastructure.getRandomString
import com.soundscape.infrastructure.toBase64Url
import com.soundscape.infrastructure.toHashed
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import se.michaelthelin.spotify.SpotifyApi
import se.michaelthelin.spotify.enums.AuthorizationScope
import se.michaelthelin.spotify.model_objects.specification.Paging
import se.michaelthelin.spotify.model_objects.specification.Track
import se.michaelthelin.spotify.model_objects.specification.User
import se.michaelthelin.spotify.requests.authorization.authorization_code.pkce.AuthorizationCodePKCERequest
import java.lang.Exception
import java.net.URI



/* ToDo: Implementing Auth Code Flow
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


    private val STATE_STRING = SpotifyConstants.STATE
    private val CODE_VERIFIER = SpotifyConstants.CODE_VERIFIER
    private val CODE_CHALLENGE = CODE_VERIFIER.toHashed("SHA-256").toBase64Url()

    // build user authorization request
/*
    private val builder: AuthorizationCodeUriRequest.Builder = AuthorizationCodeUriRequest
        .Builder()
        .client_id(SpotifyConstants.CLIENT_ID)
        .response_type(SpotifyConstants.RESPONSE_TYPE)
        .redirect_uri(URI(SpotifyConstants.REDIRECT_URI))
        .state(STATE_STRING)
        .code_challenge_method(SpotifyConstants.CODE_CHALLENGE_METHOD)
        .code_challenge(CODE_CHALLENGE)
        .scope(AuthorizationScope.USER_READ_PRIVATE, AuthorizationScope.USER_TOP_READ)

 */

    private val builder: AuthorizationRequest.Builder = AuthorizationRequest.Builder(
        SpotifyConstants.CLIENT_ID,
        SpotifyConstants.RESPONSE_TYPE, // needs to be .CODE
        SpotifyConstants.REDIRECT_URI
    )
        .setState(STATE_STRING)
        .setScopes(Array(1) { "user-read-private,user-top-read" })
        .setCustomParam(
            "code_challenge_method",
            SpotifyConstants.CODE_CHALLENGE_METHOD
        )
        .setCustomParam(
            "code_challenge",
            CODE_CHALLENGE
        )


    /* user-read private, for profile pic and name,
    *  user-top-read, gets top tracks (or artists, up to 50) from all time,
    *  last ~6months or ~4weeks.*/

    public override fun onStart() {

        super.onStart()

        /*
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
        */

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

        when (response.state) {
            STATE_STRING -> {
                when (response.type) {
                    AuthorizationResponse.Type.CODE -> {

                        val authCodePKCECredentials = AuthorizationCodePKCERequest.Builder()
                            .grant_type("authorization_code")
                            .code(response.code)
                            .redirect_uri(URI(SpotifyConstants.REDIRECT_URI))
                            .client_id(SpotifyConstants.CLIENT_ID)
                            .code_verifier(CODE_VERIFIER)
                            .build()
                            .execute()

                        val spotifyApi = SpotifyApi.Builder()
                            .setAccessToken(authCodePKCECredentials.accessToken)
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
                    } // TODO: handle Response.Type.ERROR
                    AuthorizationResponse.Type.ERROR -> {
                        Toast.makeText(
                            this,
                            "Error_Spotify_Authorization_Response_was_error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {

                    }
                }
            }
            null -> {
                Toast.makeText(
                    this,
                    "Error_Spotify_Response_State_was_null",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        finish()
    }
}