package com.example.thindie.domain.localresourceobserver.repository

import com.example.thindie.domain.localresourceobserver.entity.PossiblyWeatherLocation
import kotlinx.coroutines.flow.Flow

interface WeatherPlaceObserver {
    fun observePlaces(placeName: String): Flow<PossiblyWeatherLocation>
}