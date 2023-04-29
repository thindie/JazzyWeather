package com.example.thindie.core.localrawresources.di

import com.example.thindie.core.localrawresources.LocationProvider
import com.example.thindie.core.localrawresources.locationnameparser.LocationNameParserImpl
import com.example.thindie.core.localrawresources.locationprovider.LocationProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface LocationProviderModule{
    @Binds
    fun bindLocationProvider(impl: LocationProviderImpl): LocationProvider
}


@Module
@InstallIn(SingletonComponent::class)
internal interface LocationNameParser{
    @Binds
    fun bindLocationNameParser(impl: LocationNameParserImpl): LocationNameParser
}