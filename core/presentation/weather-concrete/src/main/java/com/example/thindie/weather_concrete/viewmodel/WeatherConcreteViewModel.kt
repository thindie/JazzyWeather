package com.example.thindie.weather_concrete.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.domain.usecases.DeleteWeatherSiteUseCase
import com.example.thindie.domain.usecases.GetDailyWeatherUseCase
import com.example.thindie.domain.usecases.RememberWeatherSiteUseCase
import com.example.thindie.domain.usecases.ReserveWeatherInteractor
import com.example.thindie.domain.usecases.timeusecases.GetHourUseCase
import com.example.thindie.domain.usecases.timeusecases.GetTimeZoneUseCase
import com.example.thindie.domain.usecases.timeusecases.GetTodayUseCase
import com.example.thindie.domain.usecases.timeusecases.GetWeekUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
internal class WeatherConcreteViewModel @Inject constructor(
    private val getTimeZoneUseCase: GetTimeZoneUseCase,
    private val getDailyWeatherUseCase: GetDailyWeatherUseCase,
    private val deleteWeatherSiteUseCase: DeleteWeatherSiteUseCase,
    private val rememberWeatherSiteUseCase: RememberWeatherSiteUseCase,
    private val getTodayUseCase: GetTodayUseCase,
    private val getWeekUseCase: GetWeekUseCase,
    private val getHourUseCase: GetHourUseCase,
    private val interactor: ReserveWeatherInteractor,
) :
    ViewModel() {

    private val _concreteScreenState: MutableStateFlow<ConcreteWeatherScreenState> =
        MutableStateFlow(ConcreteWeatherScreenState())
    val concreteScreenState: StateFlow<ConcreteWeatherScreenState> =
        _concreteScreenState
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000L),
                ConcreteWeatherScreenState()
            )

    fun onLoadConcreteScreen(forecastAble: ForecastAble?) {
        if (forecastAble != null) {
            val innerForecastAble = CurrentScreenForecastAble(
                place = forecastAble.getSight(),
                latitude = forecastAble.getSightLatitude(),
                longitude = forecastAble.getSightLongitude(),
                timezone = getTimeZoneUseCase()
            )
            viewModelScope.launch {
                getDailyWeatherUseCase(innerForecastAble)
                    .onSuccess {
                        val today = getTodayUseCase()
                        val currentWeek = getWeekUseCase(it.time)
                        val sunset = getHourUseCase(it.sunset.first())
                        val sunrise = getHourUseCase(it.sunrise.first())

                        val screenState = ConcreteWeatherScreenState(
                            weatherDaily = it,
                            isFreshForecast = true,
                            currentDay = today,
                            sunset = sunset,
                            sunrise = sunrise,
                            weekDays = currentWeek,
                        )

                        _concreteScreenState.value = screenState
                    }
                    .onFailure {

                    }
            }
        }
    }

    fun onRememberChanges(daily: WeatherDaily) {
        viewModelScope.launch {
            rememberWeatherSiteUseCase(daily)
        }
    }

    fun onDeleteLocation(place: String) {
        viewModelScope.launch { deleteWeatherSiteUseCase(place) }
    }

    data class ConcreteWeatherScreenState(
        val weatherDaily: WeatherDaily? = null,
        val isFreshForecast: Boolean = true,
        val currentDay: Int = 1,
        val sunset: String = "",
        val sunrise: String = "",
        val weekDays: List<Int> = emptyList(),
    )

    data class CurrentScreenForecastAble(
        val place: String,
        val latitude: Float,
        val longitude: Float,
        val timezone: String,
    ) : ForecastAble {
        override fun getSight(): String {
            return place
        }

        override fun getSightLatitude(): Float {
            return latitude
        }

        override fun getSightLongitude(): Float {
            return longitude
        }

        override fun getTimeZone(): String {
            return timezone
        }

    }

}
