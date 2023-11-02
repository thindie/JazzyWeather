package com.example.thindie.domain.usecases

import com.example.thindie.domain.ForecastUpdateRepository
import javax.inject.Inject

class UpdateWeatherUseCase @Inject
constructor(private val repository: ForecastUpdateRepository) {
    suspend operator fun invoke() {
        repository.update()
    }

}