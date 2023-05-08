package com.example.thindie.presentation.weatherpresenter.routing

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.thindie.presentation.routes.WeatherRoutes

fun NavGraphBuilder.selectedLocationsScreen(
    isWideScreen: Boolean,
    onSelectedDestination: (String) -> Unit
) {
    composable(
        route = WeatherRoutes.weather
    ) {

    }
}

fun NavGraphBuilder.onConcreteLocation(
    fetchContract: ConcreteScreenFetchContract,
    isWideScreen: Boolean,
    onClickBack: (String) -> Unit,
) {
    composable(
        route = WeatherRoutes.weatherConcreteLocation
    ) {
        ConcreteLocationScreenState(
            fetchContract = fetchContract,
            isWideScreen = isWideScreen,
            onClickBack = onClickBack
        )
    }
}

interface ConcreteScreenFetchContract {
    val location: String
    val latitude: Float
    val longitude: Float
}