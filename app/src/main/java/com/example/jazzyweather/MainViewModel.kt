package com.example.jazzyweather

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jazzyweather.navigation.WeatherScreen
import com.example.thindie.designsystem.utils.dangerAbleAct
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.usecases.ReserveWeatherInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(private val interactor: ReserveWeatherInteractor) :
    ViewModel() {
    private val _hottingTime = 3000L
    val hottingTime = _hottingTime.toInt()
    private val _shouldStart = mutableStateOf(false)
    val shouldStart: State<Boolean>
        get() = _shouldStart

    private val _destinationState = MutableStateFlow(WeatherScreen.ALL)
    val destinationState = _destinationState.asStateFlow()

    private val _forecastAbleState: MutableStateFlow<ForecastAble?> = MutableStateFlow(null)
    val forecastAbleState: StateFlow<ForecastAble?> = _forecastAbleState.asStateFlow()
    fun onStart() {
        dangerAbleAct {
            interactor
                .getWeatherHourlyReserveList()
                .onEach {
                    if (it.isEmpty()) _destinationState.value = WeatherScreen.LOCATION_PICKER
                }
                .launchIn(this.viewModelScope)
        }
        onShouldStart()
    }

    private fun onShouldStart() {
        viewModelScope.launch {
            delay(_hottingTime)
            _shouldStart.value = true
        }

    }

    fun onChoseForecastAble(forecastAble: ForecastAble) {
        _forecastAbleState.value = forecastAble
    }
}