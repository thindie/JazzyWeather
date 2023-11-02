package com.example.thindie.location_presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.domain.entities.WeatherLocation
import com.example.thindie.domain.usecases.DeleteWeatherSiteUseCase
import com.example.thindie.domain.usecases.FetchWeatherUseCase
import com.example.thindie.domain.usecases.ObserveHourlyWeatherListUseCase
import com.example.thindie.domain.usecases.ObserveLocationUseCase
import com.example.thindie.domain.usecases.timeusecases.GetTimeZoneUseCase
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
internal class LocationPickerViewModel @Inject constructor(
    private val getLocation: ObserveLocationUseCase,
    observeHourlyWeatherListUseCase: ObserveHourlyWeatherListUseCase,
    private val getTimeZoneUseCase: GetTimeZoneUseCase,
    private val fetchWeatherUseCase: FetchWeatherUseCase,
    private val deleteWeatherSiteUseCase: DeleteWeatherSiteUseCase,
) :
    ViewModel() {

    private val locations = MutableStateFlow(emptyList<WeatherLocation>())
    private val concreteLocation = MutableStateFlow<WeatherLocation?>(null)
    private val searchField = MutableStateFlow("")

    val uiState = combine(
        locations,
        searchField,
        concreteLocation,
        observeHourlyWeatherListUseCase()
    ) { places, input, concrete, rememberedLocations ->
        val isRemembered = rememberedLocations
            .mapNotNull { if (concrete == null || it.place != concrete.city) null else true }

        LocationsScreenState(
            locationsList = places,
            searchFieldState = input,
            focusedLocation = concrete,
            isFocusedLocationAlreadyRemembered = isRemembered.contains(true),
        )
    }
        .stateIn(
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = LocationsScreenState(
                locationsList = locations.value,
                searchFieldState = searchField.value
            ),
            scope = viewModelScope
        )

    fun onSearchReact(printedLine: String) {
        searchField.value = printedLine
        if (printedLine.isBlank()) concreteLocation.value = null
        getLocation(printedLine)
            .onEach { locationsList ->
                if (locationsList.isNotEmpty()) {
                    locations.value = locationsList
                }
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

    fun onClickWeatherLocationCard(weatherLocation: WeatherLocation?) {
        concreteLocation.value = weatherLocation
    }


    fun onClickAddFavorites(forecastAble: WeatherLocation) {
        viewModelScope.launch {
            fetchWeatherUseCase(forecastAble.copy(timezone = getTimeZoneUseCase()))
        }
    }

    data class LocationsScreenState(
        val locationsList: List<WeatherLocation> = emptyList(),
        val isLoading: Boolean = false,
        val searchFieldState: String,
        val isFocusedLocationAlreadyRemembered: Boolean = false,
        val focusedLocation: WeatherLocation? = null,
    )
}