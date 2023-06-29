package com.example.thindie.domain.localresourceobserver.di

import com.example.thindie.domain.localresourceobserver.contracts.WeatherPlacePresenter
import com.example.thindie.domain.localresourceobserver.usecase.ObservePlacesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface WeatherPlaceObserverModule {
    @Binds
    fun bindWeatherObserverUseCase(useCase: ObservePlacesUseCase): WeatherPlacePresenter
}