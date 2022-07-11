package com.soundscape

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.simplewellness.ui.theme.SoundScapeTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.soundscape.ui.*

class SoundScapeActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    //private var locationHash: String? = null

    // TODO: This should be used to filter locations using:
    // locations.filter(location -> location.hash.startsWith(hash))
    /*
    fun getLocationHash(): String? {
        return locationHash
    }

     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                locationHash = location?.let {
                    GeoHash.withCharacterPrecision(
                        it.latitude,
                        it.longitude,
                        5
                    ).toString()
                };
            }

         */

        setContent {
            SoundScapeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) { // TODO: Modify theme to match our specs
                    SoundScapeNavHost(
                        activity = this,

                    )
                }
            }
        }
    }
}

/*
TODO:
    * [x] Make a floating search bar
        * make it pass things to a function
    * Class for events
        * name
        * description
        * startEndTime
        * hostLocation
    * Get Double Row Genre List
    * Layout for Login Screen
 */
