package com.example.thindie.presentation.weatherpresenter.routing

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.thindie.presentation.routes.WeatherRoutes

fun NavGraphBuilder.selectedLocationsScreen(
    isWideScreen: Boolean,
    onSelectedDestination: (String) -> Unit
){
    composable(
        route = WeatherRoutes.weather
    ){

    }
}

fun NavGraphBuilder.onConcreteLocation(
    location: String,
    isWideScreen: Boolean,
    onClickBack: (String) -> Unit,
){
    composable(
        route = WeatherRoutes.weatherConcreteLocation
    ){

    }
}