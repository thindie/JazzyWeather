package com.example.thindie.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TransparentSystemBars() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()
    val color = MaterialTheme.colorScheme.surface

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = color,
            darkIcons = useDarkIcons
        )

        onDispose {}
    }
}