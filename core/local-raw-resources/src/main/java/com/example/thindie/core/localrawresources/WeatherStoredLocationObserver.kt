package com.example.thindie.core.localrawresources

import com.example.thindie.core.localrawresources.ldo.LocationPropertiesLdo
import kotlinx.coroutines.flow.Flow

interface WeatherStoredLocationObserver {
    fun getLocationByCoordinates(locateAble: LocateAble): Flow<List<LocationPropertiesLdo>>
    fun getLocationByStringTag(locationTag: String): Flow<List<LocationPropertiesLdo>>
}

