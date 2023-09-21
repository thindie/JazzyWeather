package com.example.thindie.location_presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.domain.entities.WeatherLocation
import com.example.thindie.domain.usecases.DeleteWeatherSiteUseCase
import com.example.thindie.domain.usecases.GetLocationUseCase
import com.example.thindie.domain.usecases.RememberWeatherSiteUseCase
import com.example.thindie.domain.usecases.timeusecases.GetTimeZoneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
internal class LocationPickerViewModel @Inject constructor(
    private val getLocation: GetLocationUseCase,
    private val getTimeZoneUseCase: GetTimeZoneUseCase,
    private val rememberWeatherSiteUseCase: RememberWeatherSiteUseCase,
    private val deleteWeatherSiteUseCase: DeleteWeatherSiteUseCase,
) :
    ViewModel() {

    private val _uiState = MutableStateFlow(LocationsScreenState())
    val uiState: StateFlow<LocationsScreenState>
        get() = _uiState.stateIn(
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = LocationsScreenState(),
            scope = viewModelScope
        )

    fun onSearchReact(printedLine: String) {
        getLocation(printedLine)
            .onEach {
                _uiState.tryEmit(LocationsScreenState(it))
            }
            .launchIn(viewModelScope)
    }

    fun onDeleteWeatherSite(place: String) {
        viewModelScope.launch {
            try {
                deleteWeatherSiteUseCase(place)
            } catch (_: Exception) {
            }

        }
    }


    fun onClickAddFavorites(forecastAble: WeatherLocation) {

        viewModelScope.launch {
            rememberWeatherSiteUseCase(forecastAble.copy(timezone = getTimeZoneUseCase()))
        }
    }

    data class LocationsScreenState(val locationsList: List<WeatherLocation> = emptyList())
}