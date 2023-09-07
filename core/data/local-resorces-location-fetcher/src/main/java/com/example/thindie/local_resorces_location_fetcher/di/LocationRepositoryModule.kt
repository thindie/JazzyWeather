package com.example.thindie.local_resorces_location_fetcher.di

import com.example.thindie.domain.LocationRepository
import com.example.thindie.local_resorces_location_fetcher.repository.LocationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface LocationRepositoryModule {
    @Binds
    fun bindLocationRepository(impl: LocationRepositoryImpl): LocationRepository
}