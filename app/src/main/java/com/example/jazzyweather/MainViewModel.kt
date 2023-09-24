package com.example.jazzyweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jazzyweather.navigation.WeatherScreen
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.usecases.ReserveWeatherInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(private val interactor: ReserveWeatherInteractor) :
    ViewModel() {

    private val _destinationState = MutableStateFlow(WeatherScreen.ALL)
    val destinationState = _destinationState.asStateFlow()

    private val _forecastAbleState: MutableStateFlow<ForecastAble?> = MutableStateFlow(null)
    val forecastAbleState: StateFlow<ForecastAble?> = _forecastAbleState.asStateFlow()
    fun onStart() {
        viewModelScope.launch {
            interactor.getWeatherHourlyReserveList()
                .ifEmpty {
                    _destinationState.value = WeatherScreen.LOCATION_PICKER
                }
        }
    }

    fun onChoseForecastAble(forecastAble: ForecastAble) {
        _forecastAbleState.value = forecastAble
    }


}