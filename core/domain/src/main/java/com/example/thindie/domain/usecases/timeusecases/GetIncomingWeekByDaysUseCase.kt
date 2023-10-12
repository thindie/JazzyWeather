package com.example.thindie.domain.usecases.timeusecases

import com.example.thindie.domain.TimeRepository
import com.example.thindie.domain.entities.ForecastDay
import javax.inject.Inject

class GetIncomingWeekByDaysUseCase @Inject constructor(private val repository: TimeRepository) {
    operator fun invoke(): List<ForecastDay> {
        return repository.getIncomingWeekByDays()
    }
}