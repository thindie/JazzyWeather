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
        SelectedLocationsScreenState(
            isWideScreen = isWideScreen,
            onSelectedDestination = onSelectedDestination
        )
    }
}

fun NavGraphBuilder.onConcreteLocation(
    fetchContract: () -> ConcreteScreenFetchContract,
    isWideScreen: Boolean,
    onClickBack: (String) -> Unit,
) {
    composable(
        route = WeatherRoutes.weatherConcreteLocation
    ) {
        ConcreteLocationScreenState(
            fetchContract = fetchContract.invoke(),
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