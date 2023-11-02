package com.example.jazzyweather.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberNavigationState(): NavigationState {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    return remember() {
        NavigationState(navController, scope)
    }
}