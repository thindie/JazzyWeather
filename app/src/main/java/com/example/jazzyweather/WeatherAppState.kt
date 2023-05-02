package com.example.jazzyweather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jazzyweather.navigation.Possibilities
import com.example.jazzyweather.navigation.weatherDestinations
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberWeatherAppState(
    isWideScreen: Boolean,
    isDarkTheme: Boolean,
    navHostController: NavHostController = rememberNavController(),
    scope: CoroutineScope = rememberCoroutineScope(),
): WeatherAppState {
    return remember(isWideScreen, isDarkTheme, navHostController, scope) {
        WeatherAppState(
            isWideScreen = isWideScreen,
            isDarkTheme = isDarkTheme,
            navHostController = navHostController,
            scope = scope
        )
    }
}


@Stable
class WeatherAppState(
    private val isWideScreen: Boolean,
    private val isDarkTheme: Boolean,
    private val navHostController: NavHostController,
    private val scope: CoroutineScope
) {
    val weatherScope
        get() =
            scope

    var locatedDestination = mutableStateOf("")

    val weatherNavHostController
        @Composable get() = navHostController

    val shouldShowRailNavigation
        @Composable get() = isWideScreen

    val isLandScape
        get() = isWideScreen

    val isShowBottomBar
        get() = !isWideScreen

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