package com.example.thindie.date_time_management.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.util.*

@Module
@InstallIn(ViewModelComponent::class)
internal class CalendarModule {
    @Provides
    fun provideCalendar(timeZone: TimeZone): Calendar {
        return Calendar.getInstance(timeZone)
    }
}