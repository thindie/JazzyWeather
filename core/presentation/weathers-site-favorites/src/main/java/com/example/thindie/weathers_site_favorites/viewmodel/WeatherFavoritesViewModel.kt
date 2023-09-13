package com.example.thindie.weathers_site_favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.designsystem.utils.act
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.domain.usecases.DeleteWeatherSiteUseCase
import com.example.thindie.domain.usecases.GetHourlyWeatherListUseCase
import com.example.thindie.domain.usecases.ReserveWeatherInteractor
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
                    _screenState.value = FavoriteWeatherSitesUiState(it)
                }
                .onFailure {
                    _screenState.value =
                        FavoriteWeatherSitesUiState(
                            reserveWeatherInteractor
                                .getWeatherHourlyReserveList()
                        )
                }

        }
    }

    fun onRemoveFavoriteWeatherPlace(weatherHourly: WeatherHourly) {
        act {
            deleteWeatherSiteUseCase(weatherHourly.place)
        }
    }

    data class FavoriteWeatherSitesUiState(val list: List<WeatherHourly>)
}
