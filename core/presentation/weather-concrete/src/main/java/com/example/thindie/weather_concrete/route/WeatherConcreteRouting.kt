package com.example.thindie.weather_concrete.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.weather_concrete.screen.WeatherConcreteScreenState

internal const val concrete = "concrete"

fun NavGraphBuilder.WeatherConcrete(
    onClickNavigation: () -> ForecastAble?,
    onClickBack: () -> Unit,
    onClickAll: () -> Unit,
) {
    composable(route = concrete) {
        WeatherConcreteScreenState(
            onClickNavigation = onClickNavigation,
            onClickBack = onClickBack,
            onClickAll = onClickAll
        )
    }

}