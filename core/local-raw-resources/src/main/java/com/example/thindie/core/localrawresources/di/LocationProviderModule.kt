package com.example.thindie.core.localrawresources.di

import com.example.thindie.core.localrawresources.LocationNameParser
import com.example.thindie.core.localrawresources.WeatherStoredLocationObserver
import com.example.thindie.core.localrawresources.locationnameparser.LocationNameParserImpl
import com.example.thindie.core.localrawresources.locationprovider.WeatherStoredLocationObserverImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface LocationProviderModule{
    @Binds
    fun bindLocationProvider(impl: WeatherStoredLocationObserverImpl): WeatherStoredLocationObserver
}


@Module
@InstallIn(SingletonComponent::class)
internal interface LocationNameParserModule{
    @Binds
    fun bindLocationNameParser(impl: LocationNameParserImpl): LocationNameParser
}