package com.example.jazzyweather

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.thindie.presentation.routes.WeatherRoutes
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberWeatherAppState(
    isWideScreen: Boolean,
    isDarkTheme: Boolean,
    navHostController: NavHostController = rememberNavController(),
    scope: CoroutineScope = rememberCoroutineScope(),
): WeatherAppState {
    Log.d ("SERVICE_TAG_remember", isWideScreen.toString())
    return remember(isWideScreen, isDarkTheme, navHostController, scope) {
        WeatherAppState(
            isWideScreen = isWideScreen,
            navHostController = navHostController,
            scope = scope
        )
    }
}


@Stable
class WeatherAppState(
    private val isWideScreen: Boolean,
    private val navHostController: NavHostController,
    private val scope: CoroutineScope
) {

    val weatherScope
        get() =
            scope

    val weatherNavHostController
        @Composable get() = navHostController

    val shouldShowRailNavigation
        @Composable get() = isWideScreen

    val isLandScape
        get() = isWideScreen

    val isShowBottomBar
        get() = !isWideScreen

    internal val startScreen
        @Composable get() = WeatherRoutes.possiblyLocation

    fun navigate(route: String) {
        navHostController.straightTo(route)
    }
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


