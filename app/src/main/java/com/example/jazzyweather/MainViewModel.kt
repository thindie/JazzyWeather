package com.example.jazzyweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jazzyweather.navigation.WeatherScreen
import com.example.thindie.domain.RatificationObserver
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.usecases.FetchWeatherUseCase
import com.example.thindie.domain.usecases.UpdateWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    @Named("volumeController") private val controller: RatificationObserver,
    private val updateWeatherUseCase: UpdateWeatherUseCase,
    private val fetchWeatherUseCase: FetchWeatherUseCase,
) :
    ViewModel() {
    private val _hottingTime = 1000L
    val hottingTime = _hottingTime

    init {
        viewModelScope.launch {
            updateWeatherUseCase()
        }
    }

    val destinationState = controller.observeRatification()
        .map { permission ->
            if (permission.isAllowed()) {
                WeatherScreen.ALL
            } else {
                WeatherScreen.LOCATION_PICKER
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            WeatherScreen.LOCATION_PICKER
        )


    private val _forecastAbleState: MutableStateFlow<ForecastAble?> = MutableStateFlow(null)
    val forecastAbleState: StateFlow<ForecastAble?> = _forecastAbleState.asStateFlow()


    fun onChoseForecastAble(forecastAble: ForecastAble) {
        _forecastAbleState.value = forecastAble
    }

    fun onRequestFetch(forecastAble: ForecastAble) {
        viewModelScope.launch {
            fetchWeatherUseCase.invoke(forecastAble)
        }
    }
}