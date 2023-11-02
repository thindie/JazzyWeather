package com.example.thindie.weather_concrete.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.designsystem.DecodeAble
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.ForecastDay
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.domain.usecases.DeleteWeatherSiteUseCase
import com.example.thindie.domain.usecases.GetHourlyWeatherByDateUseCase
import com.example.thindie.domain.usecases.ObserveDailyWeatherUseCase
import com.example.thindie.domain.usecases.RememberChangesWeatherSiteUseCase
import com.example.thindie.domain.usecases.timeusecases.GetHourUseCase
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
    private val observeDailyWeatherUseCase: ObserveDailyWeatherUseCase,
    private val deleteWeatherSiteUseCase: DeleteWeatherSiteUseCase,
    private val getTodayUseCase: GetTodayUseCase,
    private val getWeekUseCase: GetWeekUseCase,
    private val getHourUseCase: GetHourUseCase,
    private val getIncomingWeekByDaysUseCase: GetIncomingWeekByDaysUseCase,
    private val getSimpleDateUseCase: GetSimpleDateUseCase,
    private val decodeAble: DecodeAble,
    private val rememberChangesWeatherSiteUseCase: RememberChangesWeatherSiteUseCase,
    private val getHourlyWeatherByDateUseCase: GetHourlyWeatherByDateUseCase,
) :
    ViewModel() {

    private val isHourlyLoading = MutableStateFlow(true)

    private val forecastAbleEvent = MutableStateFlow<ForecastAble?>(null)
    private val concreteHourly = MutableStateFlow<WeatherHourly?>(null)
    private val concreteDaily = observeDailyWeatherUseCase(forecastAbleEvent.filterNotNull())


    val concreteScreenState: StateFlow<ConcreteWeatherScreenState> =
        combine(
            concreteDaily,
            concreteHourly,
        ) { concrete, hourly ->

            val today = getTodayUseCase()
            val currentWeek = getWeekUseCase(concrete.time)
            val currentWeekByDaysNaming = getIncomingWeekByDaysUseCase()
            val sunset = getHourUseCase(concrete.sunset.first())
            val sunrise = getHourUseCase(concrete.sunrise.first())

            ConcreteWeatherScreenState(
                weatherDaily = concrete,
                concreteWeatherHourly = hourly?.rawTimeTo24hHours(),
                isFreshForecast = true,
                currentDay = today,
                sunset = sunset,
                sunrise = sunrise,
                weekDays = currentWeek,
                isLoading = false,
                isHourlyLoading = false,
                namedWeekDays = currentWeekByDaysNaming
            )
        }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000L),
                ConcreteWeatherScreenState()
            )


    fun onDecodeWeatherCode(code: Int): Int {
        return decodeAble.decodeDrawable(code)
    }


    fun onLoadConcreteScreen(forecastAble: ForecastAble?) {

        if (forecastAble != null) {
            forecastAbleEvent.value = forecastAble.timeZoneApproved()
        }
    }

    fun onRememberChanges(daily: WeatherDaily) {
        viewModelScope.launch {
            rememberChangesWeatherSiteUseCase.invoke(daily)
        }
    }

    fun onDeleteLocation(place: String) {
        viewModelScope.launch {
            deleteWeatherSiteUseCase(place)
        }
    }

    private fun ForecastAble.timeZoneApproved() = CurrentScreenForecastAble(
        place = getSight(),
        latitude = getSightLatitude(),
        longitude = getSightLongitude(),
        timezone = getTimeZoneUseCase()
    )

    fun onClickedConcreteWeekDay(timeInMillis: Long) {
        isHourlyLoading.value = true
        viewModelScope.launch {
            val simpleDate = getSimpleDateUseCase(timeInMillis)
            val currentForecastAble = forecastAbleEvent.value
            if (currentForecastAble != null)
                getHourlyWeatherByDateUseCase.invoke(
                    simpleDate, currentForecastAble.timeZoneApproved()
                ).apply {
                    concreteHourly.value = this

                }
        }

    }

    private fun WeatherHourly.rawTimeTo24hHours(): WeatherHourly {
        return copy(time = time.map {
            getHourUseCase(it)
        })
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
        val hour: Int = 0,
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

