package com.example.thindie.domain

import kotlinx.coroutines.flow.Flow

interface EventBus<T> {

    fun observeEvents(): Flow<EventClass<T>>
}
