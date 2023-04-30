package com.example.thindie.core.localrawresources

import com.example.thindie.core.localrawresources.ldo.LocationPropertiesLdo
import kotlinx.coroutines.flow.Flow

interface WeatherStoredLocationObserver {
    fun getLocationByCoordinates(coordinates: Coordinates): Flow<LocationPropertiesLdo>
    fun getLocationByStringTag(locationTag: String): Flow<LocationPropertiesLdo>
}

interface Coordinates {
    val latitude: Float
    val longitude: Float
}