package com.example.jazzyweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jazzyweather.navigation.WeatherScreen
import com.example.thindie.domain.RatificationObserver
import com.example.thindie.domain.entities.ForecastAble
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class MainViewModel @Inject constructor(@Named("volumeController") private val controller: RatificationObserver) :
    ViewModel() {
    private val _hottingTime = 4000L
    val hottingTime = _hottingTime

    private val _destinationState = controller.observeRatification()
        .onEmpty { WeatherScreen.LOCATION_PICKER }
        .map { permission ->
            if (permission.isAllowed())
                WeatherScreen.ALL
            else WeatherScreen.LOCATION_PICKER
        }

    val destinationState = _destinationState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000L),
        WeatherScreen.LOCATION_PICKER
    )

    private val _forecastAbleState: MutableStateFlow<ForecastAble?> = MutableStateFlow(null)
    val forecastAbleState: StateFlow<ForecastAble?> = _forecastAbleState.asStateFlow()


    fun onChoseForecastAble(forecastAble: ForecastAble) {
        _forecastAbleState.value = forecastAble
    }
}