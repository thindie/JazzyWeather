package com.example.thindie.location_presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.domain.entities.WeatherLocation
import com.example.thindie.domain.usecases.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
internal class LocationPickerViewModel @Inject constructor(private val getLocation: GetLocationUseCase) :
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

    data class LocationsScreenState(val locationsList: List<WeatherLocation> = emptyList())
}