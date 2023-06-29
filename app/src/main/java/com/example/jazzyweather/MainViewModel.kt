package com.example.jazzyweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.presentation.weatherpresenter.routing.ConcreteScreenFetchContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _concreteScreenLocationProperties = MutableStateFlow(LandedConcreteScreenContract())
    val concreteScreenLocationProperties: StateFlow<ConcreteScreenFetchContract> =
        _concreteScreenLocationProperties
            .stateIn(
                viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                LandedConcreteScreenContract(),
            )


    fun updateConcreteLocationProperties(
        location: String,
        latitude: Float,
        longitude: Float
    ) {
        _concreteScreenLocationProperties.value = LandedConcreteScreenContract(
            location, latitude, longitude
        )
    }

    data class LandedConcreteScreenContract(
        override val location: String = "",
        override val latitude: Float = 1f,
        override val longitude: Float = 1f
    ) : ConcreteScreenFetchContract
}