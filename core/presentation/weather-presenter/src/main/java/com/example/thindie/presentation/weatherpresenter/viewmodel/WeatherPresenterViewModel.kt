package com.example.thindie.presentation.weatherpresenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.domain.weatherprovider.contract.WeatherOperator
import com.example.thindie.domain.weatherprovider.entity.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
internal class WeatherPresenterViewModel @Inject constructor(private val fetcher: WeatherOperator) :
    ViewModel() {

    private val weatherPresenterUiState: MutableStateFlow<WeatherPresenterUIState> =
        MutableStateFlow(WeatherPresenterUIState.ErrorWeather(NoSuchElementException())
        )

    val weatherPresenterUIStateFlow = weatherPresenterUiState.asStateFlow()

    fun onSwitchPinWeather(locationName: String) {
        viewModelScope.launch {
            fetcher.pinWeather(locationName)
        }
    }

    fun onShowLocationWeather(locationName: String) {
        viewModelScope.launch {
            val state = try {
                WeatherPresenterUIState
                    .SuccessWeatherPlace(fetcher.fetchWeather(locationName))
            } catch (e: IllegalStateException) {
                WeatherPresenterUIState
                    .ErrorWeather(e)
            }
            weatherPresenterUiState.value = state
        }
    }

    fun onShowPinnedWeathers() {
        viewModelScope.launch {
            val state = try {
                WeatherPresenterUIState
                    .SuccessWeatherPinnedPlaces(fetcher.fetchPinnedWeatherLocations())
            } catch (e: IllegalStateException) {
                WeatherPresenterUIState
                    .ErrorWeather(e)
            }
            weatherPresenterUiState.value = state
        }
    }

    sealed class WeatherPresenterUIState {
        data class SuccessWeatherPlace(val place: Weather) : WeatherPresenterUIState()
        data class SuccessWeatherPinnedPlaces(val places: List<Weather>) :
            WeatherPresenterUIState()
        data class ErrorWeather(val e: Exception) : WeatherPresenterUIState()
    }
}