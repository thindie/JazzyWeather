package com.example.thindie.weather_fetcher.di

import com.example.thindie.domain.RatificationAble
import com.example.thindie.weather_fetcher.fetchcontroller.FetchController
import com.example.thindie.weather_fetcher.fetchcontroller.NetWorkController
import com.example.thindie.weather_fetcher.fetchcontroller.TimeController
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
    fun bindRatificationAble(controller: FetchController): RatificationAble

    @Binds
    @Named("networkController")
    fun bindNetRatificationAble(controller: NetWorkController): RatificationAble

    @Binds
    @Named("timeController")
    fun bindTimeRatificationAble(controller: TimeController): RatificationAble

}