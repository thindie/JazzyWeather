package com.example.thindie.date_time_management.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
internal class SimpleDateFormatModule {


    @Provides
    @Named("week_day")
    fun provideSimpleDateFormat(timePattern: TimePatterns): SimpleDateFormat {
        return SimpleDateFormat(timePattern.patternWeekDay(), Locale.getDefault())
    }

    @Provides
    @Named("month")
    fun provideSimpleDateFormatMonth(timePattern: TimePatterns): SimpleDateFormat {
        return SimpleDateFormat(timePattern.patternMonth(), Locale.getDefault())
    }

    @Provides
    @Named("year")
    fun provideSimpleDateFormatYear(timePattern: TimePatterns): SimpleDateFormat {
        return SimpleDateFormat(timePattern.patternYear(), Locale.getDefault())
    }

    @Provides
    @Named("hour")
    fun provideSimpleDateFormatHour(timePattern: TimePatterns): SimpleDateFormat {
        return SimpleDateFormat(timePattern.patternHour(), Locale.getDefault())
    }

    @Provides
    @Named("ISO8601")
    fun provideSimpleDateFormat8601(timePattern: TimePatterns): SimpleDateFormat {
        return SimpleDateFormat(timePattern.patternIso(), Locale.getDefault())
    }

    @Provides
    @Named("simpleDate")
    fun provideSimpleDateFormatSimpleDate(timePattern: TimePatterns): SimpleDateFormat {
        return SimpleDateFormat(timePattern.patternSimple(), Locale.getDefault())
    }

    @Provides
    @Named("simple_8106")
    fun provideSimpleDateFormatSimple8106Date(timePattern: TimePatterns): SimpleDateFormat {
        return SimpleDateFormat(timePattern.patternSimple8106(), Locale.getDefault())
    }

    @Provides
    @Named("stringDay")
    fun provideSimpleDateFormatStringDay(timePattern: TimePatterns): SimpleDateFormat {
        return SimpleDateFormat(timePattern.patternStringWeek(), Locale.getDefault())
    }
}