package com.example.jazzyweather.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberNavigationState(): NavigationState {
    val navController = rememberNavController()
    return remember() {
        NavigationState(navController)
    }
}