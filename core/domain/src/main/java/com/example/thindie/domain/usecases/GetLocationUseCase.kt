package com.example.thindie.domain.usecases

import com.example.thindie.domain.LocationRepository
import com.example.thindie.domain.entities.WeatherLocation
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetLocationUseCase @Inject constructor(private val repository: LocationRepository) {
    operator fun invoke(currentResponse: String): Flow<List<WeatherLocation>> {
        return repository.observeLocationCurrentRequest(currentResponse)
    }
}