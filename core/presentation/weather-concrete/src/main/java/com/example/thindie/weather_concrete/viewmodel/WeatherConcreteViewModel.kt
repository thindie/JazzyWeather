package com.example.thindie.weather_concrete.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.designsystem.utils.dangerAbleAct
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.ForecastDay
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.domain.usecases.DeleteWeatherSiteUseCase
import com.example.thindie.domain.usecases.GetDailyWeatherUseCase
import com.example.thindie.domain.usecases.ReserveWeatherInteractor
import com.example.thindie.domain.usecases.timeusecases.GetHourUseCase
import com.example.thindie.domain.usecases.timeusecases.GetHourlyWeatherOnConcreteDateUseCase
import com.example.thindie.domain.usecases.timeusecases.GetIncomingWeekByDaysUseCase
import com.example.thindie.domain.usecases.timeusecases.GetSimpleDateUseCase
import com.example.thindie.domain.usecases.timeusecases.GetTimeZoneUseCase
import com.example.thindie.domain.usecases.timeusecases.GetTodayUseCase
import com.example.thindie.domain.usecases.timeusecases.GetWeekUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@Suppress("LongParameterList")
@HiltViewModel
internal class WeatherConcreteViewModel @Inject constructor(
    private val getTimeZoneUseCase: GetTimeZoneUseCase,
    private val getDailyWeatherUseCase: GetDailyWeatherUseCase,
    private val deleteWeatherSiteUseCase: DeleteWeatherSiteUseCase,
    private val getTodayUseCase: GetTodayUseCase,
    private val getWeekUseCase: GetWeekUseCase,
    private val getHourUseCase: GetHourUseCase,
    private val getIncomingWeekByDaysUseCase: GetIncomingWeekByDaysUseCase,
    private val interactor: ReserveWeatherInteractor,
    private val getSimpleDateUseCase: GetSimpleDateUseCase,
    private val getHourlyWeatherOnConcreteDateUseCase: GetHourlyWeatherOnConcreteDateUseCase,
) :
    ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    private val _isHourlyForecastLoading = MutableStateFlow(true)
    private val _concreteWeatherHourly = MutableStateFlow<WeatherHourly?>(null)

    private var lastFetchedForecastAble: ForecastAble? = null

    private val _concreteScreenState: MutableStateFlow<WeatherDaily?> =
        MutableStateFlow(null)
    val concreteScreenState: StateFlow<ConcreteWeatherScreenState> =
        combine(
            _concreteScreenState.filterNotNull(),
            _isLoading,
            _isHourlyForecastLoading,
            _concreteWeatherHourly
        ) { concrete, loading, hourlyLoading, hourlyWeather ->

            val today = getTodayUseCase()
            val currentWeek = getWeekUseCase(concrete.time)
            val currentWeekByDaysNaming = getIncomingWeekByDaysUseCase()
            val sunset = getHourUseCase(concrete.sunset.first())
            val sunrise = getHourUseCase(concrete.sunrise.first())

            ConcreteWeatherScreenState(
                weatherDaily = concrete,
                concreteWeatherHourly = hourlyWeather,
                isFreshForecast = true,
                currentDay = today,
                sunset = sunset,
                sunrise = sunrise,
                weekDays = currentWeek,
                isLoading = loading,
                isHourlyLoading = hourlyLoading,
                namedWeekDays = currentWeekByDaysNaming
            )
        }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000L),
                ConcreteWeatherScreenState()
            )

    fun onLoadConcreteScreen(forecastAble: ForecastAble?) {
        if (forecastAble != null && forecastAble != lastFetchedForecastAble) {
            lastFetchedForecastAble = forecastAble
            val innerForecastAble = forecastAble.timeZoneApproved()

            dangerAbleAct {
                _isLoading.value = true
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
        _isLoading.value = false
        _concreteScreenState.value = weatherDaily
    }

    private fun ForecastAble.timeZoneApproved() = CurrentScreenForecastAble(
        place = getSight(),
        latitude = getSightLatitude(),
        longitude = getSightLongitude(),
        timezone = getTimeZoneUseCase()
    )

    fun onClickedConcreteWeekDay(timeInMillis: Long) {
        _isHourlyForecastLoading.value = true
        viewModelScope.launch {
            val simpleDate = getSimpleDateUseCase(timeInMillis)
            getHourlyWeatherOnConcreteDateUseCase.invoke(
                forecastAble = lastFetchedForecastAble!!.timeZoneApproved(),
                simpleDate
            ).onSuccess {
                _isHourlyForecastLoading.value = false
                _concreteWeatherHourly.value = it
            }.onFailure {
                _isHourlyForecastLoading.value = false
                _concreteWeatherHourly.value = null
            }
        }
     }


    data class ConcreteWeatherScreenState(
        val weatherDaily: WeatherDaily? = null,
        val concreteWeatherHourly: WeatherHourly? = null,
        val isFreshForecast: Boolean = true,
        val currentDay: Int = 1,
        val sunset: String = "",
        val sunrise: String = "",
        val weekDays: List<Int> = emptyList(),
        val namedWeekDays: List<ForecastDay> = emptyList(),
        val isLoading: Boolean = true,
        val isHourlyLoading: Boolean = false,
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
