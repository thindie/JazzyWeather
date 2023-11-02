package com.example.thindie.weather_fetcher.repository

import com.example.thindie.core.localrawresources.di.DispatchersIOModule
import com.example.thindie.domain.EventBus
import com.example.thindie.domain.EventClass
import com.example.thindie.domain.EventTransmitter
import com.example.thindie.weather_fetcher.EventKind
import com.example.thindie.weather_fetcher.mappers.toRes
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn

@Singleton
internal class FetchEventBusImpl
@Inject constructor(@DispatchersIOModule.IODispatcher private val dispatchersIO: CoroutineDispatcher) :
    EventBus<Int>,
    EventTransmitter<EventKind> {

    private val _eventFlow = MutableStateFlow<EventClass<Int>?>(null)
    override fun observeEvents(): Flow<EventClass<Int>> {
        return _eventFlow.filterNotNull().flowOn(dispatchersIO)
    }

    override fun send(eventClass: EventClass<EventKind>) {
        _eventFlow.value = eventClass.toRes()
    }
}