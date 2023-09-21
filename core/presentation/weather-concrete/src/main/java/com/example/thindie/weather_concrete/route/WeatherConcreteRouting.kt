package com.example.thindie.weather_concrete.route

import androidx.compose.runtime.Composable
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.weather_concrete.screen.WeatherConcreteScreenState

@Composable
fun WeatherConcreteRouting(onClickNavigation: () -> ForecastAble) {
    WeatherConcreteScreenState(onClickNavigation = onClickNavigation)
}