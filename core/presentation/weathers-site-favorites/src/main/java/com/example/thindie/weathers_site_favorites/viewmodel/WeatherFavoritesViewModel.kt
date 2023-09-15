package com.example.thindie.weathers_site_favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.designsystem.utils.act
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.domain.usecases.DeleteWeatherSiteUseCase
import com.example.thindie.domain.usecases.GetHourlyWeatherListUseCase
import com.example.thindie.domain.usecases.ReserveWeatherInteractor
import com.example.thindie.domain.usecases.timeusecases.GetCurrentHourOfDayUseCase
import com.example.thindie.domain.usecases.timeusecases.GetHourUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class WeatherFavoritesViewModel @Inject constructor(
    private val getHourlyWeatherListUseCase: GetHourlyWeatherListUseCase,
    private val deleteWeatherSiteUseCase: DeleteWeatherSiteUseCase,
    private val reserveWeatherInteractor: ReserveWeatherInteractor,
    private val getCurrentHourOfDayUseCase: GetCurrentHourOfDayUseCase,
    private val getHourUseCase: GetHourUseCase,
) :
    ViewModel() {

    private val _screenState = MutableStateFlow(FavoriteWeatherSitesUiState(emptyList()))
    val screenState = _screenState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000L),
        FavoriteWeatherSitesUiState(emptyList())
    )

    fun onSelectFavoriteWeatherPlacesScreen() {
        act {
            getHourlyWeatherListUseCase()
                .onSuccess {
                    _screenState.value = FavoriteWeatherSitesUiState(
                        list = it.rawTimeTo24hHours(),
                        currentHour = getCurrentHourOfDayUseCase()
                    )
                }
                .onFailure {
                    _screenState.value =
                        FavoriteWeatherSitesUiState(
                            list = reserveWeatherInteractor
                                .getWeatherHourlyReserveList()
                                .rawTimeTo24hHours(),
                            currentHour = getCurrentHourOfDayUseCase()
                        )
                }

        }
    }

    fun onRemoveFavoriteWeatherPlace(weatherHourly: WeatherHourly) {
        act {
            deleteWeatherSiteUseCase(weatherHourly.place)
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

    data class FavoriteWeatherSitesUiState(val list: List<WeatherHourly>, val currentHour: Int = 0)
}
