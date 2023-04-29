package com.example.thindie.core.network.di

import com.example.thindie.core.network.WeatherProvider
import com.example.thindie.core.network.weatherproviderimpl.WeatherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface WeatherProviderModule {
    @Binds
    fun bindWeatherProvider(impl: WeatherProviderImpl): WeatherProvider
}