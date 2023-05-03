package com.example.thindie.domain.weatherprovider.di

import com.example.thindie.domain.weatherprovider.contract.WeatherOperator
import com.example.thindie.domain.weatherprovider.usecase.FetchWeatherUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface WeatherFetcherModule {
    @Binds
    fun bindWeatherFetcher(useCase: FetchWeatherUseCase): WeatherOperator
}