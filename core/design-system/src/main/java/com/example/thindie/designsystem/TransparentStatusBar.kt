package com.example.thindie.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TransparentSystemBars(isInDarkTheme: Boolean) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isInDarkTheme
    val color = MaterialTheme.colorScheme.surface

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = color,
            darkIcons = useDarkIcons
        )

        onDispose {}
    }
}