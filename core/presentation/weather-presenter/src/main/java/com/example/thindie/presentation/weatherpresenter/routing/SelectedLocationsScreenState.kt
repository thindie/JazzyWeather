package com.example.thindie.presentation.weatherpresenter.routing

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.presentation.weatherpresenter.screen.selectedweathers.SelectedWeatherLocationsScreen
import com.example.thindie.presentation.weatherpresenter.viewmodel.WeatherPinnedPlacesViewModel

@Composable
internal fun SelectedLocationsScreenState(
    viewModel: WeatherPinnedPlacesViewModel = hiltViewModel(),
    isWideScreen: Boolean,
    onSelectedDestination: (String, Float, Float) -> Unit,
) {
    viewModel.onShowPinnedWeathers()
    val screenState = viewModel
        .weatherPlacesUIState
        .collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)
    when (screenState.value) {
        is WeatherPinnedPlacesViewModel.WeatherPresenterUIState.SuccessWeatherPinnedPlaces -> {
            SelectedWeatherLocationsScreen(
                isWideScreen = isWideScreen,
                onSelectedDestination = onSelectedDestination,
                onChangePinnedStatus = viewModel::onSwitchPinWeather,
                weatherList =
                (screenState.value as WeatherPinnedPlacesViewModel
                .WeatherPresenterUIState.SuccessWeatherPinnedPlaces).places
            )
        }
        is WeatherPinnedPlacesViewModel.WeatherPresenterUIState.ErrorWeather -> {}
    }


}