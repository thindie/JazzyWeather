package com.example.thindie.weather_fetcher.di

import com.example.thindie.domain.WeatherRepository
import com.example.thindie.weather_fetcher.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface WeatherRepositoryModule {
    @Binds
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository
}