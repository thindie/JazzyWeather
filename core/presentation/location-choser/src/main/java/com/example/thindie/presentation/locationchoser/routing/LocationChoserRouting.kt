package com.example.thindie.presentation.locationchoser.routing

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.State
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.thindie.presentation.locationchoser.screen.LocationChooserScreen
import com.example.thindie.presentation.routes.WeatherRoutes

fun NavGraphBuilder.possiblyLocationChoseScreen(
    locationListState: LazyListState,
    isWideScreen: Boolean,
    onSelectedLocation: (String) -> Unit
) {
    composable(
        route = WeatherRoutes.possiblyLocation
    ) {
        LocationChooserScreen(
            isWideScreen = isWideScreen,
            onSelectedLocation = onSelectedLocation,
            locationListState = locationListState
        )
    }
}