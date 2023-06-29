package com.example.thindie.presentation.locationchoser.viewmodel

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.domain.localresourceobserver.contracts.WeatherPlacePresenter
import com.example.thindie.domain.localresourceobserver.entity.PossiblyWeatherLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class LocationChooserViewModel @Inject constructor(private val locationsPresenter: WeatherPlacePresenter) :
    ViewModel() {
    private val _possiblyWeatherLocations: MutableStateFlow<List<Location?>> =
        MutableStateFlow(emptyList())
    val possiblyWeatherLocations: StateFlow<List<Location?>> =
        _possiblyWeatherLocations.asStateFlow()

    fun onReactiveSearch(searchState: State<String>) {
        locationsPresenter.presentPlaces(searchState.value)
            .filter { searchState.value.isNotBlank() }
            .map { possiblyLocationList ->
                possiblyLocationList
                    .map { possiblyLocation -> possiblyLocation.toLocation() }
            }
            .onEach { place -> _possiblyWeatherLocations.value = place }
            .launchIn(viewModelScope)
    }

    data class Location(
        val adminName: String,
        val city: String,
        val latitude: String,
        val longitude: String,
        val population: String,
    )

    private fun PossiblyWeatherLocation.toLocation() =
        Location(
            this.adminName,
            this.city,
            this.latitude,
            this.longitude,
            this.population,
        )
}

