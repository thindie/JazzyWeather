package com.example.thindie.data.weatherfetcher.di

import com.example.thindie.data.weatherfetcher.repositoryimpl.WeatherFetcherRepositoryImpl
import com.example.thindie.domain.weatherprovider.repository.WeatherFetcherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface FetcherRepositoryModule {
    @Binds
    fun bindFetcherRepository(impl: WeatherFetcherRepositoryImpl): WeatherFetcherRepository
}