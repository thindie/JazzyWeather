package com.example.thindie.presentation.weatherpresenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.domain.weatherprovider.contract.WeatherOperator
import com.example.thindie.domain.weatherprovider.entity.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
internal class WeatherPinnedPlacesViewModel @Inject constructor(private val fetcher: WeatherOperator) :
    ViewModel() {

    private val weatherPresenterUiState: MutableStateFlow<WeatherPresenterUIState> =
        MutableStateFlow(
            WeatherPresenterUIState.ErrorWeather(NoSuchElementException())
        )

    val weatherPlacesUIState = weatherPresenterUiState
        .stateIn(
            viewModelScope, started = SharingStarted.WhileSubscribed(5_000L),
            WeatherPresenterUIState.ErrorWeather(NoSuchElementException())
        )


    fun onSwitchPinWeather(locationName: String) {
        viewModelScope.launch {
            fetcher.pinWeather(locationName)
        }
    }


    fun onShowPinnedWeathers() {
        viewModelScope.launch {
            fetcher.fetchPinnedWeatherLocations()
                .onSuccess {
                    weatherPresenterUiState.value = WeatherPresenterUIState
                        .SuccessWeatherPinnedPlaces(it)
                }
                .onFailure {
                    weatherPresenterUiState.value = WeatherPresenterUIState.ErrorWeather(it)
                }


        }
    }

    sealed class WeatherPresenterUIState {
        data class SuccessWeatherPinnedPlaces(val places: List<Weather>) :
            WeatherPresenterUIState()

        data class ErrorWeather(val e: Throwable) : WeatherPresenterUIState()
    }
}


