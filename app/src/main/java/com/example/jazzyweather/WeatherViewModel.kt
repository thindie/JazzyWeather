package com.example.jazzyweather

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jazzyweather.data.toPossibility
import com.example.jazzyweather.di.DispatchersModule
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.domain.Weather
import com.example.jazzyweather.domain.WeatherOffline
import com.example.jazzyweather.domain.abstractions.unpackResult
import com.example.jazzyweather.domain.useCases.*
import com.example.jazzyweather.ui.WeatherHourlyUIModel
import com.example.jazzyweather.ui.WeatherUIModel
import com.example.jazzyweather.ui.map
import com.example.jazzyweather.ui.mapHourly
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val add: AddFavoriteUseCase,
    private val delete: DeleteFavoriteUseCase,
    private val search: SearchLocationUseCase,
    private val request: RequestWeatherUseCase,
    private val favors: GetFavoriteLocationsUseCase,
    private val get: GetSavedPossibilitiesUseCase,
    private val hourly: GetHourlyWeatherUseCase,
    private val offline: GetOfflineWeatherUseCase,
    @DispatchersModule.IODispatcher private val IO: CoroutineDispatcher,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    private val _favorites = MutableStateFlow<List<WeatherHourlyUIModel>>(emptyList())
    private val _weather = MutableStateFlow<WeatherUIModel?>(null)
    private val _possibility = MutableStateFlow<List<Possibility>>(emptyList())
    private val _offline = MutableStateFlow<List<WeatherOffline>>(emptyList())

    val viewState: StateFlow<ViewState> =
        combine(
            _favorites,
            _isLoading,
            _weather,
            _possibility,
            _offline

            ) { favorites, isLoading, weather, possibility, offline ->
            if (!isLoading) {
                if (weather == null) {
                    if(offline.isNotEmpty()){ ViewState( offLine = offline, isLoading = isLoading )  }
                    if (favorites.isEmpty()) {
                        ViewState(isLoading = isLoading, possibility = possibility)
                    } else {
                        ViewState(
                            isLoading = isLoading,
                            favorites = favorites,
                            possibility = possibility
                        )
                    }
                } else {
                    ViewState(isLoading = isLoading, weather = weather)
                }
            } else
                ViewState()
        }.stateIn(
            scope = viewModelScope,
            initialValue = ViewState(),
            started = SharingStarted.WhileSubscribed(3000)
        )

    fun onFavoriteAdd(weather: WeatherUIModel) {
        viewModelScope.launch {
            add(weather.map())
        }

    }

    fun onRequestFavorites() {
        cleanState()
        _isLoading.value = true
        viewModelScope.launch {
            val favorite: List<Weather> = favors().unpackResult() ?: emptyList()
            if (favorite.isNotEmpty()) {
                _favorites.value = flowOf(favorite).map {
                    it.map {
                        hourly(it.toPossibility())
                            .unpackResult()!!.let { it1 -> it.mapHourly(it1) }
                    }
                }.stateIn(viewModelScope).value
            }
        }

        viewModelScope.launch {
            _possibility.value = get().unpackResult() ?: emptyList()
            _isLoading.value = _possibility.value.isEmpty()
        }
        TODO()
    }

    fun onFavoriteDelete(id: String) {
        viewModelScope.launch {
            delete(id)
        }
    }

    fun onClickWeather(weather: WeatherHourlyUIModel) {
        _weather.value = weather.map()
    }

    fun onSearch(tag: String) {
        viewModelScope.launch {
            cleanState()
            _isLoading.value = true
            _possibility.value = search(tag).map {
                it.unpackResult() ?: emptyList()
            }.stateIn(viewModelScope).value.apply {
                _isLoading.value = this.isEmpty()
            }
        }
    }

    fun onRequest(possibility: Possibility) {
        cleanState()
        viewModelScope.launch {
            _isLoading.value = true
            _weather.value = withContext(IO) {
                request(possibility).unpackResult()?.map()
            }
            _isLoading.value = (_weather.value == null)
            TODO()
        }

    }

    private fun cleanState() {
        _weather.value = null
        _favorites.value = emptyList()
        _possibility.value = emptyList()
        _isLoading.value = false
        _offline.value = emptyList()
    }
}

data class ViewState(
    val isLoading: Boolean = false,
    val favorites: List<WeatherHourlyUIModel> = emptyList(),
    val weather: WeatherUIModel? = null,
    val offLine: List<WeatherOffline> = emptyList(),
    val possibility: List<Possibility> = emptyList(),
)