package com.example.thindie.weather_fetcher.di

import com.example.thindie.domain.RatificationAble
import com.example.thindie.domain.RatificationObserver
import com.example.thindie.weather_fetcher.fetchcontroller.FetchController
import com.example.thindie.weather_fetcher.fetchcontroller.NetWorkController
import com.example.thindie.weather_fetcher.fetchcontroller.TimeController
import com.example.thindie.weather_fetcher.repository.WeatherFetchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
internal interface RatificationAbleModule {
    @Binds
    @Named("fetchController")
    fun bindRatificationAble(controller: FetchController): RatificationObserver

    @Binds
    @Named("networkController")
    fun bindNetRatificationAble(controller: NetWorkController): RatificationObserver

    @Binds
    @Named("timeController")
    fun bindTimeRatificationAble(controller: TimeController): RatificationAble

    @Binds
    @Named("volumeController")
    fun bindRepoRatificationAble(controller: WeatherFetchRepositoryImpl): RatificationObserver


}