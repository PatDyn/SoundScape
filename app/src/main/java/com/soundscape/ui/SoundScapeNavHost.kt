package com.soundscape.ui

import android.app.Activity
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.soundscape.SoundScapeScreen

//@Preview(showBackground = true)
//@Composable
//fun SoundScapePreview(){
//    SoundScapeApp(this)
//}

@Composable
fun SoundScapeNavHost(activity: Activity?) {
    val context =  LocalContext.current
    val navController = rememberNavController()
    var clickedIndex by remember { mutableStateOf(0) }
    val bottomActionViewModel = BottomActionViewModel(context)
    var modifier = Modifier

    // ToDo: Set MainBody as start destination when user has logged in with a service/ or input data

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
                modifier = modifier,
                activity = activity
            )
        }

        composable(SoundScapeScreen.Main.name) {
            MainBody(
                modifier = modifier,
                clickedIndex = clickedIndex,
                viewModel = bottomActionViewModel,
                onClickGoToDetailsScreen = { index: Int -> clickedIndex = index; navController.navigate(
                    SoundScapeScreen.Detail.name) }
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