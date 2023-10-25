package com.example.thindie.weather_fetcher.di

import com.example.thindie.domain.ForecastAbleRepository
import com.example.thindie.domain.ForecastUpdateRepository
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.weather_fetcher.repository.WeatherDailyRepositoryImpl
import com.example.thindie.weather_fetcher.repository.WeatherFetchRepositoryImpl
import com.example.thindie.weather_fetcher.repository.WeatherHourlyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface WeatherRepositoryModule {
    @Binds
    fun bindWeatherRepository(impl: WeatherDailyRepositoryImpl): ForecastAbleRepository<WeatherDaily>

    @Binds
    fun bindReserveBus(bus: WeatherHourlyRepositoryImpl): ForecastAbleRepository<WeatherHourly>

    @Binds
    fun bindUpdater(impl: WeatherFetchRepositoryImpl): ForecastUpdateRepository
}