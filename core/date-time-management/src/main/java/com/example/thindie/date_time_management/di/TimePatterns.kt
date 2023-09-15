package com.example.thindie.date_time_management.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
internal object TimePatterns {
    @Named("pattern_week_day")
    @Provides
    fun patternWeekDay() = "dddd"

    @Named("pattern_month")
    @Provides
    fun patternMonth() = "MMM"

    @Named("pattern_year")
    @Provides
    fun patternYear() = "yyyy"

}