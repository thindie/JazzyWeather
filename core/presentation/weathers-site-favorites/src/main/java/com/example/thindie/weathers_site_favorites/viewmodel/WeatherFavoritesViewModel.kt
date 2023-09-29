package com.example.thindie.weathers_site_favorites.viewmodel

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.designsystem.DecodeAble
import com.example.thindie.designsystem.utils.act
import com.example.thindie.designsystem.utils.dangerAbleAct
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.domain.usecases.ObserveHourlyWeatherListUseCase
import com.example.thindie.domain.usecases.RequestHourlyWeatherListUseCase
import com.example.thindie.domain.usecases.timeusecases.GetCurrentHourOfDayUseCase
import com.example.thindie.domain.usecases.timeusecases.GetHourUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.stateIn


@HiltViewModel
class WeatherFavoritesViewModel @Inject constructor(
    private val observeHourlyWeatherListUseCase: ObserveHourlyWeatherListUseCase,
    private val getCurrentHourOfDayUseCase: GetCurrentHourOfDayUseCase,
    private val getHourUseCase: GetHourUseCase,
    private val decodeAble: DecodeAble,
    private val requester: RequestHourlyWeatherListUseCase,
) :
    ViewModel() {

    private var isAlreadyObserve = false

    private val _screenState =
        MutableStateFlow(FavoriteWeatherSitesUiState(emptyList()))

    val screenState = _screenState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000L),
        FavoriteWeatherSitesUiState(list = emptyList())
    )

    @Inject
    fun onStart() {
        act {
            if (!isAlreadyObserve) {
                isAlreadyObserve = true
                observeHourlyWeatherListUseCase
                    .invoke()
                    .onEmpty {
                        declareEmpty()
                    }
                    .onEach { list ->
                        list.onStateRenew()
                    }
                    .launchIn(viewModelScope)
            }
        }
    }

    fun onSelectFavoriteWeatherPlacesScreen() {
        dangerAbleAct {
            requester()
        }
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

    private fun declareEmpty() {
        _screenState.value = FavoriteWeatherSitesUiState(
            list = emptyList()
        )
    }

    private fun List<WeatherHourly>.onStateRenew() {
        _screenState.value =
            FavoriteWeatherSitesUiState(
                list = rawTimeTo24hHours(),
                currentHour = getCurrentHourOfDayUseCase(),
                decodedWeather = decodeAble.decodeString(getCurrentWeatherCode()),
                decodedWeatherDrawable = decodeAble.decodeDrawable(getCurrentWeatherCode())
            )

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
        val currentHour: Int = 0,
        @StringRes val decodedWeather: Int = 0,
        @DrawableRes val decodedWeatherDrawable: Int = 0,
    )
}
