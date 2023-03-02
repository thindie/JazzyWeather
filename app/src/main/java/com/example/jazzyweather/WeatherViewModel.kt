package com.example.jazzyweather

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jazzyweather.di.DispatchersModule
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.domain.Weather
import com.example.jazzyweather.domain.unpackResult
import com.example.jazzyweather.domain.useCases.*
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
    private val save: SavePossibilitiesUseCase,
    private val get: GetSavedPossibilitiesUseCase,
    private val offline: GetOfflineWeatherUseCase,
    @DispatchersModule.IODispatcher private val IO : CoroutineDispatcher
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

    fun onClickWeather(weather: Weather) {
        _weather.value = weather
    }

    fun onSearch(tag: String) {
         cleanState()
        _isLoading.value = true
        search(tag).onEach {
            _isLoading.value = false
            _possibility.value = it.unpackResult() ?: emptyList()
        }.launchIn(viewModelScope)
    }

    fun onRequest(possibility: Possibility) {
         viewModelScope.launch {
       _weather.value =  withContext(IO){
                 request(possibility).unpackResult()
             }
         }


    }
    private fun cleanState(){
        _weather.value = null
        _favorites.value = emptyList()
        _possibility.value = emptyList()
        _isLoading.value = false
    }
}

data class ViewState(
    val isSelectedPlace: Boolean = true,
    val isLoading: Boolean = false,
    val favorites: List<Weather> = emptyList(),
    val weather: Weather? = null,
    val possibility: List<Possibility> = emptyList(),
)