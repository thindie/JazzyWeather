package com.example.thindie.domain

import com.example.thindie.domain.entities.WeatherLocation
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun observeLocationCurrentRequest(currentRequest: String): Flow<List<WeatherLocation>>
}