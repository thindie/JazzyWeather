package com.example.thindie.date_time_management.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.util.*

@Module
@InstallIn(ViewModelComponent::class)
internal class TimeZoneModule {
    @Provides
    fun provideTimeZone(): TimeZone {
        return TimeZone.getDefault()
    }
}