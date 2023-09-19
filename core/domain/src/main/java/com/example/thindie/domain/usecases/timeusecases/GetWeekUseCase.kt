package com.example.thindie.domain.usecases.timeusecases

import com.example.thindie.domain.TimeRepository
import javax.inject.Inject

class GetWeekUseCase @Inject constructor(private val repository: TimeRepository) {
    operator fun invoke(iso8106: List<String>): List<Int> {
        return repository.getWeekInNumbers(iso8106)
    }
}