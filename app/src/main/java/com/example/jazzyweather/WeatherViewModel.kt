package com.example.jazzyweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.domain.Weather
import com.example.jazzyweather.domain.unpackResult
import com.example.jazzyweather.domain.useCases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val add: AddFavoriteUseCase,
    private val delete: DeleteFavoriteUseCase,
    private val search: SearchLocationUseCase,
    private val request: RequestWeatherUseCase,
    private val favors: GetFavoriteLocationsUseCase,
    private val offline: GetOfflineWeatherUseCase,
) : ViewModel() {

    private val _isLoading = MutableStateFlow<Boolean>(false)
    private val _isSelectedPlace = MutableStateFlow<Boolean>(true)
    private val _favorites = MutableStateFlow<List<Weather>>(emptyList())
    private val _weather = MutableStateFlow<Weather?>(null)
    private val _possibility = MutableStateFlow<List<Possibility>>(emptyList())


    val viewState: StateFlow<ViewState> =
        combine(
            _isSelectedPlace,
            _favorites,
            _isLoading,
            _weather,
            _possibility
        ) { isSelectedPlace, favorites, isLoading, weather, possibility ->
            if (!isSelectedPlace) {
                ViewState(isSelectedPlace = false)
            }
            if (!isLoading) {
                if (weather == null) {
                    if (favorites.isEmpty()) {
                        ViewState(isLoading = isLoading, possibility = possibility)
                    } else {
                        ViewState(isLoading = isLoading, favorites = favorites)
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

    fun onStart() {
        viewModelScope.launch {
            _isLoading.value = true
            _favorites.value = favors().unpackResult() ?: emptyList<Weather>().apply {
                _isSelectedPlace.value = false
            }
            _isLoading.value = false
        }
    }

    fun onFavoriteAdd(weather: Weather) {
        viewModelScope.launch {
            add(weather)
        }

    }

    fun onFavoriteDelete(id: String) {
        viewModelScope.launch {
            delete(id)
        }
    }

    fun onSearch(tag: String) {
        viewModelScope.launch {
            _isLoading.value = true
            search(tag).collect {
                _isLoading.value = false
                _possibility.value = it.unpackResult() ?: emptyList()//TODO() stub

            }
        }
    }

    fun onRequest(possibility: Possibility) {
        viewModelScope.launch {
            _isLoading.value = true
            request(possibility).unpackResult().apply {
                if (this == null) offline().collect {
                    it.unpackResult()//TODO
                    _weather.value = null
                }
                else _isLoading.value = false; _weather.value = this

            }
        }
    }
}

data class ViewState(
    val isSelectedPlace: Boolean = true,
    val isLoading: Boolean = false,
    val favorites: List<Weather> = emptyList(),
    val weather: Weather? = null,
    val possibility: List<Possibility> = emptyList(),
)