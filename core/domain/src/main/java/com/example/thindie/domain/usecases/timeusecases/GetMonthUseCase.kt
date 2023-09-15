package com.example.thindie.domain.usecases.timeusecases

import com.example.thindie.domain.TimeRepository
import javax.inject.Inject

class GetMonthUseCase @Inject constructor(private val repository: TimeRepository) {
    operator fun invoke(): String {
        return repository.getCurrentMonth()
    }
}