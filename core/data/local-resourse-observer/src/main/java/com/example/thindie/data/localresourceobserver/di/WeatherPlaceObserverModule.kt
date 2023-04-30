package com.example.thindie.data.localresourceobserver.di

import com.example.thindie.data.localresourceobserver.repositoryimpl.WeatherPlaceObserverImpl
import com.example.thindie.domain.localresourceobserver.repository.WeatherPlaceObserver
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface WeatherPlaceObserverModule {
    @Binds
    fun bindWeatherPlaceObserever(impl: WeatherPlaceObserverImpl): WeatherPlaceObserver
}