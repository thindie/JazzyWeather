package com.example.jazzyweather.ui.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jazzyweather.ui.composables.util.FavoriteWeathers
import com.example.jazzyweather.ui.composables.util.Offline
import com.example.jazzyweather.ui.composables.util.weatherDestinations

@Composable
fun WeatherAppUI() {
    val navController = rememberNavController()
    val currentBackStack = navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack.value?.destination
    val currentScreen =
        weatherDestinations.find { it.route == currentDestination?.route } ?: Offline

    /*WeatherNavHost(
        navController = navController,
        startDestination = currentScreen,
    )*/
}

fun NavController.straightTo(route: String) {
    this.navigate(route) {
        popUpTo(this@straightTo.graph.findStartDestination().id) {
            saveState = true
        }
        restoreState = true
        launchSingleTop = true
    }
}