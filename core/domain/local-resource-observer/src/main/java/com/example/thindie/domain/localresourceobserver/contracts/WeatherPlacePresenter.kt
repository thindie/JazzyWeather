package com.example.thindie.domain.localresourceobserver.contracts

import com.example.thindie.domain.localresourceobserver.entity.PossiblyWeatherLocation
import kotlinx.coroutines.flow.Flow

interface WeatherPlacePresenter {
    fun presentPlaces(possiblyLocationName: String): Flow<List<PossiblyWeatherLocation>>
}