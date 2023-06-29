package com.example.thindie.presentation.weatherpresenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.domain.weatherprovider.contract.WeatherFetchRequirements
import com.example.thindie.domain.weatherprovider.contract.WeatherOperator
import com.example.thindie.domain.weatherprovider.entity.Weather
import com.example.thindie.presentation.weatherpresenter.routing.ConcreteScreenFetchContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
internal class WeatherPresenterViewModel @Inject constructor(private val fetcher: WeatherOperator) :
    ViewModel() {

    private val weatherPresenterUiState: MutableStateFlow<WeatherPresenterUIState> =
        MutableStateFlow(
            WeatherPresenterUIState.ErrorWeather(NoSuchElementException())
        )

    val weatherPresenterUIStateFlow = weatherPresenterUiState.asStateFlow()

    fun onPinWeather(locationName: String, latitude: Float, longitude: Float) {
        viewModelScope.launch {
            val contract = object : ConcreteScreenFetchContract {
                override val location: String
                    get() = locationName
                override val latitude: Float
                    get() = latitude
                override val longitude: Float
                    get() = longitude

            }
            fetcher.pinWeather(contract.map())
        }
    }

    fun onShowLocationWeather(fetchContract: ConcreteScreenFetchContract) {
        viewModelScope.launch {
            fetcher.fetchWeather(fetchContract.map())
                .onFailure {
                    weatherPresenterUiState.value = WeatherPresenterUIState.ErrorWeather(it)
                }
                .onSuccess {
                    weatherPresenterUiState.value = WeatherPresenterUIState.SuccessWeatherPlace(it)
                }
        }
    }


    sealed class WeatherPresenterUIState {
        data class SuccessWeatherPlace(val place: Weather) : WeatherPresenterUIState()
        data class ErrorWeather(val e: Throwable) : WeatherPresenterUIState()
    }
}

 fun ConcreteScreenFetchContract.map() = object : WeatherFetchRequirements {
    override val location: String
        get() = this@map.location
    override val latitude: Float
        get() = this@map.latitude
    override val longitude: Float
        get() = this@map.longitude

}
