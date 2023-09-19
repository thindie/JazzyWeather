package com.example.thindie.weather_concrete.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.weather_concrete.viewmodel.WeatherConcreteViewModel

@Composable
internal fun WeatherConcreteScreenState(
    onClickNavigation: () -> ForecastAble,
    viewModel: WeatherConcreteViewModel = hiltViewModel(),
) {
    viewModel.onLoadConcreteScreen(onClickNavigation())
    val screenState =
        viewModel.concreteScreenState
            .collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)
    WeatherConcreteScreen(screenState = screenState.value)
}