package com.example.thindie.weather_fetcher.di

import com.example.thindie.domain.EventBus
import com.example.thindie.domain.EventTransmitter
import com.example.thindie.weather_fetcher.EventKind
import com.example.thindie.weather_fetcher.repository.FetchEventBusImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface EventBusModule {
    @Binds
    fun bindEventBus(eventBusImpl: FetchEventBusImpl): EventBus<Int>

    @Binds
    fun bindEventTransmit(eventBusImpl: FetchEventBusImpl): EventTransmitter<EventKind>
}