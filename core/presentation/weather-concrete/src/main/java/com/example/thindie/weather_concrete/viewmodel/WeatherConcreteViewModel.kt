package com.example.thindie.weather_concrete.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.designsystem.utils.dangerAbleAct
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.domain.usecases.DeleteWeatherSiteUseCase
import com.example.thindie.domain.usecases.GetDailyWeatherUseCase
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

@HiltViewModel
internal class WeatherConcreteViewModel @Inject constructor(
    private val getTimeZoneUseCase: GetTimeZoneUseCase,
    private val getDailyWeatherUseCase: GetDailyWeatherUseCase,
    private val deleteWeatherSiteUseCase: DeleteWeatherSiteUseCase,
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
            val innerForecastAble = forecastAble.timeZoneApproved()

            dangerAbleAct {
                getDailyWeatherUseCase(innerForecastAble)
                    .onSuccess {
                        renewScreenStateValue(it)
                    }
                    .onFailure {
                        try {
                            val reserveDaily = interactor.getDailyWeatherSite(innerForecastAble)
                            renewScreenStateValue(reserveDaily)
                        } catch (_: Exception) {

                        }

                    }
            }
        }
    }

    fun onRememberChanges(daily: WeatherDaily) {
        dangerAbleAct {
            interactor.updateWeatherSite(daily)
        }
    }

    fun onDeleteLocation(place: String) {
        dangerAbleAct {
            deleteWeatherSiteUseCase(place)
        }
    }

    private fun renewScreenStateValue(weatherDaily: WeatherDaily) {
        val today = getTodayUseCase()
        val currentWeek = getWeekUseCase(weatherDaily.time)
        val sunset = getHourUseCase(weatherDaily.sunset.first())
        val sunrise = getHourUseCase(weatherDaily.sunrise.first())

        val screenState = ConcreteWeatherScreenState(
            weatherDaily = weatherDaily,
            isFreshForecast = true,
            currentDay = today,
            sunset = sunset,
            sunrise = sunrise,
            weekDays = currentWeek,
        )

        _concreteScreenState.value = screenState
    }

    private fun ForecastAble.timeZoneApproved() = CurrentScreenForecastAble(
        place = getSight(),
        latitude = getSightLatitude(),
        longitude = getSightLongitude(),
        timezone = getTimeZoneUseCase()
    )


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
