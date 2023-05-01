package com.example.jazzyweather

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.jazzyweather.navigation.weatherDestinations
import com.example.thindie.presentation.designsystem.asBarFiller
import com.example.thindie.presentation.designsystem.bottomnavbar.NavBottomBar
import com.example.thindie.presentation.designsystem.monosystemscreens.WindowSizeDependableContent
import com.example.thindie.presentation.designsystem.searchbar.SearchBar
import com.example.thindie.presentation.locationchoser.routing.locationChoseScreen
import com.example.thindie.presentation.routes.WeatherRoutes
import com.example.thindie.presentation.weatherpresenter.routing.onConcreteLocation
import com.example.thindie.presentation.weatherpresenter.routing.selectedLocationsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherApp(
    isSystemDarkThemed: Boolean,
    isLandScapeOrientation: Boolean,
    navHostController: NavHostController = rememberNavController(),
    appState: WeatherAppState = rememberWeatherAppState(
        isWideScreen = isLandScapeOrientation,
        isDarkTheme = isSystemDarkThemed,
        navHostController = navHostController
    )
) {
    Scaffold(
        bottomBar = {
            NavBottomBar(
                onSelectedDestination = appState::navigate,
                onOperateCurrentContent = {},
                actionsBarStart = weatherDestinations.map { destination ->
                    asBarFiller(destination.icon, destination.route)
                },
                actionBarEnd = null
            )
        },
        topBar = {
            if (appState.currentScreen.route == WeatherRoutes.possiblyLocation) SearchBar(
                onSearch = { appState.setLocationAndNavigate(it) })
        }
    ) { values ->
        WindowSizeDependableContent(
            modifier = Modifier.padding(values),
            isLandscape = appState.shouldShowRailNavigation,
            onClick = appState::navigate,
            navigationRailPoints =
            weatherDestinations.map { destination ->
                asBarFiller(destination.icon, destination.route)
            }
        ) {
            NavHost(
                navController = navHostController,
                startDestination = appState.currentScreen.route
            ) {
                onConcreteLocation(
                    location = appState.locatedDestination.value,
                    isWideScreen = appState.isLandScape,
                    onClickBack = appState::navigate
                )
                selectedLocationsScreen(
                    isWideScreen = appState.isLandScape,
                    onSelectedDestination = appState::navigate
                )
                locationChoseScreen(
                    isWideScreen = appState.isLandScape,
                    onSelectedLocation = {
                            appState.setLocationAndNavigate(it)
                    }
                )
            }

        }

    }
}

private fun WeatherAppState.setLocationAndNavigate(location: String){
    this.locatedDestination.value = location
    this.navigate(WeatherRoutes.weatherConcreteLocation)
}
