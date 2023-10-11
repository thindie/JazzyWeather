package com.example.thindie.domain.usecases.timeusecases

import com.example.thindie.domain.TimeRepository
import javax.inject.Inject

class GetIncomingWeekByDaysUseCase @Inject constructor(private val repository: TimeRepository) {
    operator fun invoke(): List<String> {
        return repository.getIncomingWeekByDays()
    }
}