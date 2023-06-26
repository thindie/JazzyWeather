package com.example.thindie.presentation.weatherpresenter.routing

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.presentation.weatherpresenter.screen.concreteweather.WeatherPresenterScreen
import com.example.thindie.presentation.weatherpresenter.viewmodel.WeatherPresenterViewModel

@Composable
internal fun ConcreteLocationScreenState(
    viewModel: WeatherPresenterViewModel = hiltViewModel(),
    isWideScreen: Boolean,
    onClickBack: (String) -> Unit,
    fetchContract: ConcreteScreenFetchContract,
) {
    val viewState = viewModel.weatherPresenterUIStateFlow.collectAsStateWithLifecycle()
    viewModel.onShowLocationWeather(fetchContract)
    when (viewState.value) {
        is WeatherPresenterViewModel
        .WeatherPresenterUIState
        .SuccessWeatherPlace,
        -> WeatherPresenterScreen(
            weather = (viewState.value as WeatherPresenterViewModel
            .WeatherPresenterUIState.SuccessWeatherPlace).place,
            isWideScreen = isWideScreen,
            onClick = viewModel::onSwitchPinWeather
        )
        is WeatherPresenterViewModel.WeatherPresenterUIState.ErrorWeather -> {}
        else -> {}
    }

}