package com.soundscape

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplewellness.ui.theme.SoundScapeTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.soundscape.ui.*

class MainActivity : ComponentActivity() {
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
                    SoundScapeApp()
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

@Preview(showBackground = true)
@Composable
fun SoundScapePreview(){
    SoundScapeApp()
}

@Composable
fun SoundScapeApp() {
    val context =  LocalContext.current
    val navController = rememberNavController()
    var clickedIndex by remember { mutableStateOf(0) }
    val bottomActionViewModel = BottomActionViewModel(context)
    var modifier = Modifier

    NavHost(
        navController = navController,
        startDestination = SoundScapeScreen.StartOff.name
    ) {
        composable(SoundScapeScreen.StartOff.name) {
            StartOffBody(
                modifier = modifier,
                onClickGoToLoginScreen = {navController.navigate(SoundScapeScreen.Login.name)},
                onClickContinueLocal = {navController.navigate(SoundScapeScreen.Main.name)}
            )
        }

        composable(SoundScapeScreen.Login.name) {
            LoginBody(
                modifier = modifier
            )
        }

        composable(SoundScapeScreen.Main.name) {
            MainBody(
                modifier = modifier,
                clickedIndex = clickedIndex,
                viewModel = bottomActionViewModel,
                onClickGoToDetailsScreen = { index: Int -> clickedIndex = index; navController.navigate(SoundScapeScreen.Detail.name) }
            )
        }
        composable(SoundScapeScreen.Detail.name) {
            DetailsBody(
                bottomActionViewModel = bottomActionViewModel,
                modifier = modifier
            )
        }
    }
}


