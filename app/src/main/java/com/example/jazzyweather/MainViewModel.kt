package com.example.jazzyweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jazzyweather.navigation.WeatherScreen
import com.example.thindie.domain.entities.ForecastAble
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor() :
    ViewModel() {
    private val _hottingTime = 4000L
    val hottingTime = _hottingTime.toInt()

    private val _destinationState = MutableStateFlow(WeatherScreen.ALL)
    val destinationState = _destinationState.asStateFlow()

    private val _forecastAbleState: MutableStateFlow<ForecastAble?> = MutableStateFlow(null)
    val forecastAbleState: StateFlow<ForecastAble?> = _forecastAbleState.asStateFlow()
    fun onStart() {
        viewModelScope.launch {

        }
    }

    fun onChoseForecastAble(forecastAble: ForecastAble) {
        _forecastAbleState.value = forecastAble
    }
}