package com.example.thindie.domain.usecases.utilusecases

import com.example.thindie.domain.EventBus
import com.example.thindie.domain.EventClass
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveEventsUseCase @Inject constructor(private val eventBus: EventBus<Int>) {
    operator fun invoke(): Flow<EventClass<Int>> {
        return eventBus.observeEvents()
    }
}