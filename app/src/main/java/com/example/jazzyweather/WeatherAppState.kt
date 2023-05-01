package com.example.jazzyweather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jazzyweather.navigation.Possibilities
import com.example.jazzyweather.navigation.weatherDestinations

@Composable
fun rememberWeatherAppState(
    isWideScreen: Boolean,
    isDarkTheme: Boolean,
    navHostController: NavHostController
): WeatherAppState {
    return remember(isWideScreen, isDarkTheme, navHostController) {
        WeatherAppState(
            isWideScreen = isWideScreen,
            isDarkTheme = isDarkTheme,
            navHostController = navHostController
        )
    }
}


@Stable
class WeatherAppState(
    private val isWideScreen: Boolean,
    private val isDarkTheme: Boolean,
    private val navHostController: NavHostController
) {
    var locatedDestination = mutableStateOf("")


    val shouldShowRailNavigation
        @Composable get() = isWideScreen

    val isLandScape
        get() = isWideScreen

    val isDarkThemed
        @Composable get() = isDarkTheme

    private val navEntry
        @Composable get() = navHostController.currentBackStackEntryAsState()

    private val currentDestination
        @Composable get() = navEntry.value?.destination
    internal val currentScreen
        @Composable get() =
            weatherDestinations.find { it.route == currentDestination?.route } ?: Possibilities


    fun navigate(route: String) {
        navHostController.straightTo(route)
    }

    private fun NavController.straightTo(route: String) {
        this.navigate(route) {
            popUpTo(this@straightTo.graph.findStartDestination().id) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }

}