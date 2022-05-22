package com.soundscape

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ch.hsr.geohash.GeoHash
import com.example.simplewellness.ui.theme.SoundScapeTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.soundscape.userinterface.LoginBody
import com.soundscape.userinterface.MainBody
import com.soundscape.userinterface.StartOffBody

class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var locationHash: String? = null

    // TODO: This should be used to filter locations using:
    // locations.filter(location -> location.hash.startsWith(hash))
    fun getLocationHash(): String? {
        return locationHash
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        setContent {
            SoundScapeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) { // TODO: Modify theme to match our specs
                    SoundScapeApp()
                }
            }
        }
    }
}

/*
TODO:
    * Make a floating search bar
        * make it pass things to a function
 */

@Preview(showBackground = true)
@Composable
fun SoundScapePreview(){
    SoundScapeApp()
}

@Composable
fun SoundScapeApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SoundScapeScreen.StartOff.name
    ) {
        composable(SoundScapeScreen.StartOff.name) {
            StartOffBody(
                onClickGoToLoginScreen = {navController.navigate(SoundScapeScreen.Login.name)},
                onClickContinueLocal = {navController.navigate(SoundScapeScreen.Main.name)}
            )
        }

        composable(SoundScapeScreen.Login.name) {
            LoginBody()
        }

        composable(SoundScapeScreen.Main.name) {
            MainBody()
        }

    }
}


