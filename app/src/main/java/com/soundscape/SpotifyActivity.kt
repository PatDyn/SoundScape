package com.soundscape

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.adamratzman.spotify.*
import com.adamratzman.spotify.auth.pkce.AbstractSpotifyPkceLoginActivity
import com.soundscape.infrastructure.SpotifyConstants

internal var pkceClassBackTo: Class<out Activity>? = null

class SpotifyActivity : AbstractSpotifyPkceLoginActivity() {
    override val clientId = SpotifyConstants.CLIENT_ID
    override val redirectUri = SpotifyConstants.REDIRECT_URI
    override val scopes = SpotifyScope.values().toList()

    override fun onSuccess(api: SpotifyClientApi) {
        val model = (application as SoundScapeApplication).model
        model.credentialStore.setSpotifyApi(api)
        val classBackTo = pkceClassBackTo ?: MainActivity::class.java
        pkceClassBackTo = null
        Toast.makeText(
            this,
            "Authentication via PKCE has completed. Launching ${classBackTo.simpleName}..",
            Toast.LENGTH_SHORT
        ).show()
        startActivity(Intent(this, classBackTo))
    }

    override fun onFailure(exception: Exception) {
        exception.printStackTrace()
        pkceClassBackTo = MainActivity::class.java
        Toast.makeText(
            this,
            "Auth failed: ${exception.message}",
            Toast.LENGTH_SHORT
        ).show()
    }
}

//class SpotifyActivity : Activity() {
//
//    private val STATE_STRING = SpotifyConstants.STATE
//    private val CODE_VERIFIER = SpotifyConstants.CODE_VERIFIER
//    private val CODE_CHALLENGE = CODE_VERIFIER.toHashed("SHA-256").toBase64Url()
//
//    val pkceUrl: String = getSpotifyPkceAuthorizationUrl(
//        SpotifyScope.USER_READ_PRIVATE,
//        SpotifyScope.USER_TOP_READ,
//        clientId = SpotifyConstants.CLIENT_ID,
//        redirectUri = SpotifyConstants.REDIRECT_URI,
//        codeChallenge = CODE_CHALLENGE,
//        state = STATE_STRING
//    )
//     private val builder: AuthorizationRequest.Builder = AuthorizationRequest.Builder(
//        SpotifyConstants.CLIENT_ID,
//        SpotifyConstants.RESPONSE_TYPE, // needs to be .CODE
//        SpotifyConstants.REDIRECT_URI
//    )
//        .setState(STATE_STRING)
//        .setScopes(Array(1) { "user-read-private,user-top-read" })
//        .setCustomParam(
//            "code_challenge_method",
//            SpotifyConstants.CODE_CHALLENGE_METHOD
//        )
//        .setCustomParam(
//            "code_challenge",
//            CODE_CHALLENGE
//        )
//
//
//    /* user-read private, for profile pic and name,
//    *  user-top-read, gets top tracks (or artists, up to 50) from all time,
//    *  last ~6months or ~4weeks.*/
//
//    public override fun onStart() {
//
//        super.onStart()
//
//        //TODO if (SPOTIFY_TOKEN == "") bla bla open login activity ( "" is default value)
//
//        val policy = ThreadPolicy.Builder().permitNetwork().build()
//        StrictMode.setThreadPolicy(policy)
//
//        val request: AuthorizationRequest = builder.build()
//        AuthorizationClient.openLoginActivity(
//            this,
//            SpotifyConstants.AUTH_TOKEN_REQUEST_CODE,
//            request)
//
//        /*AuthorizationClient.openLoginActivity(
//            this,
//            AUTH_TOKEN_REQUEST_CODE as Int,
//            request
//        )*/
//    }
//
//    override fun onNewIntent(intent: Intent?) {
//
//        super.onNewIntent(intent);
//
//        val uri = intent?.data
//        val response = AuthorizationResponse.fromUri(uri)
//
//        when (response.state) {
//            STATE_STRING -> {
//                when (response.type) {
//                    AuthorizationResponse.Type.CODE -> {
//
//                        val spotifyApi = spotifyClientPkceApi(
//                            SpotifyConstants.CLIENT_ID,
//                            SpotifyConstants.REDIRECT_URI,
//                            response.code,
//                            CODE_VERIFIER
//                        ){
//                            retryWhenRateLimited = false
//                        }.build()
//
//
//                    } // TODO: handle Response.Type.ERROR
//                    AuthorizationResponse.Type.ERROR -> {
//                        Toast.makeText(
//                            this,
//                            "Error_Spotify_Authorization_Response_was_error",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                    else -> {
//
//                    }
//                }
//            }
//            null -> {
//                Toast.makeText(
//                    this,
//                    "Error_Spotify_Response_State_was_null",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//        finish()
//    }
//}