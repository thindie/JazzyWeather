package com.example.thindie.date_time_management.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*

@Module
@InstallIn(SingletonComponent::class)
internal class TimeZoneModule {
    @Provides
    fun provideTimeZone(): TimeZone {
        return TimeZone.getDefault()
    }
}