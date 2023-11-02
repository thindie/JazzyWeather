package com.example.thindie.domain.usecases.timeusecases

import com.example.thindie.domain.TimeRepository
import javax.inject.Inject

class GetSimpleDateUseCase @Inject constructor(private val repository: TimeRepository) {
    operator fun invoke(millis: Long): String {
        return repository.getSimpleDateFromMillis(millis)
    }
}