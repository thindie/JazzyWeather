package com.example.thindie.presentation.locationchoser.routing

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.thindie.presentation.routes.WeatherRoutes

fun NavGraphBuilder.locationChoseScreen(
    isWideScreen: Boolean,
    onSelectedLocation: (String) -> Unit
){
    composable(
        route = WeatherRoutes.possiblyLocation
    ){

    }
}