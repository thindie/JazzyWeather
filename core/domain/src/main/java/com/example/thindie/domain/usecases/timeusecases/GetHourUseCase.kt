package com.example.thindie.domain.usecases.timeusecases

import com.example.thindie.domain.TimeRepository
import javax.inject.Inject

class GetHourUseCase @Inject constructor(private val repository: TimeRepository) {
    operator fun invoke(dateInString: String): String {
        return repository.getCurrentHour(dateInString)
    }
}