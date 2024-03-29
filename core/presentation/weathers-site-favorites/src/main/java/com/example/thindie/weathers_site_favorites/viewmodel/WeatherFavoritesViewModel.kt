package com.example.thindie.weathers_site_favorites.viewmodel

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.designsystem.DecodeAble
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.domain.usecases.ObserveHourlyWeatherListUseCase
import com.example.thindie.domain.usecases.UpdateWeatherUseCase
import com.example.thindie.domain.usecases.timeusecases.GetCurrentHourOfDayUseCase
import com.example.thindie.domain.usecases.timeusecases.GetHourUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


@HiltViewModel
class WeatherFavoritesViewModel @Inject constructor(
    private val observeHourlyWeatherListUseCase: ObserveHourlyWeatherListUseCase,
    private val getCurrentHourOfDayUseCase: GetCurrentHourOfDayUseCase,
    private val getHourUseCase: GetHourUseCase,
    private val requestUpdate: UpdateWeatherUseCase,
    private val decodeAble: DecodeAble,
) :
    ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    private val _weathers = MutableStateFlow(emptyList<WeatherHourly>())

    val screenState = combine(
        _isLoading,
        _weathers,
    )
    { loading, list ->
        FavoriteWeatherSitesUiState(
            list = list.rawTimeTo24hHours(),
            isLoading = loading,
            currentHour = getCurrentHourOfDayUseCase(),
            decodedWeather = decodeAble.decodeString(list.getCurrentWeatherCode()),
            decodedWeatherDrawable = decodeAble.decodeDrawable(list.getCurrentWeatherCode())
        )
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            FavoriteWeatherSitesUiState(list = emptyList(), isLoading = true)
        )

    @Inject
    fun onStart() {
        viewModelScope.launch {
            observeHourlyWeatherListUseCase()
                .onEach {
                    _isLoading.value = false
                    _weathers.value = it
                }.launchIn(this)
        }
    }

    fun onRequestRefresh() {
        viewModelScope.launch {
            requestUpdate.invoke()
        }
    }

    fun onSelectFavoriteWeatherPlacesScreen() {
        viewModelScope.launch {
        }
    }

    fun onDecodeWeatherCode(code: Int): Int {
        return decodeAble.decodeDrawable(code)
    }

    private fun List<WeatherHourly>.getCurrentWeatherCode(): Int {
        val currentHour = getCurrentHourOfDayUseCase()
        return try {
            this[currentHour]
                .weatherCode[currentHour]
        } catch (_: Exception) {
            0
        }
    }


    private fun List<WeatherHourly>.rawTimeTo24hHours(): List<WeatherHourly> {
        return map { weatherHourly ->
            weatherHourly
                .copy(time = weatherHourly.time.map {
                    getHourUseCase(it)
                })
        }
    }

    data class FavoriteWeatherSitesUiState(
        val list: List<WeatherHourly>,
        val isLoading: Boolean,
        val currentHour: Int = 0,
        @StringRes val decodedWeather: Int = 0,
        @DrawableRes val decodedWeatherDrawable: Int = 0,
    )
}
