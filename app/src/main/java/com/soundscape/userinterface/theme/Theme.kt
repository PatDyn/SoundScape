package com.example.simplewellness.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = PurpleAction,

    //secondary = Teal200,
    surface = Grey300,
    background = Grey500,
    onSurface = Grey200,
    onBackground = White
)

private val LightColorPalette = lightColors(
    primary = PurpleAction,

    surface = White,
    background = Grey100,
    onSurface = Grey200,
    onBackground = Black100


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun SoundScapeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )

    val systemUiController = rememberSystemUiController()



    if(darkTheme){
        systemUiController.setNavigationBarColor(
            color = Grey500,
        )
        {color -> Grey200

        }
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false
        )

    }else{
        systemUiController.setNavigationBarColor(
            color = Grey100
        )
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true
        )
    }
}
