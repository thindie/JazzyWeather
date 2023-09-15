package com.example.thindie.date_time_management.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.text.SimpleDateFormat
import javax.inject.Named


@Module
@InstallIn(ViewModelComponent::class)
class SimpleDateFormatModule {
    @Provides
    @Named("week_day")
    fun provideSimpleDateFormat(@Named("pattern_week_day") pattern: String): SimpleDateFormat {
        return SimpleDateFormat(pattern)
    }

    @Provides
    @Named("month")
    fun provideSimpleDateFormatMonth(@Named("pattern_month") pattern: String): SimpleDateFormat {
        return SimpleDateFormat(pattern)
    }

    @Provides
    @Named("year")
    fun provideSimpleDateFormatYear(@Named("pattern_year") pattern: String): SimpleDateFormat {
        return SimpleDateFormat(pattern)
    }

}