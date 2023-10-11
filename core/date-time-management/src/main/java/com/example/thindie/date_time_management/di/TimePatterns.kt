package com.example.thindie.date_time_management.di

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class TimePatterns @Inject constructor() {

    fun patternWeekDay(): String {
        return "d"
    }

    fun patternMonth(): String {
        return "MMM"
    }

    fun patternYear(): String {
        return "yyyy"
    }

    fun patternHour(): String {
        return "HH:mm"
    }

    fun patternIso(): String {
        return "yyyy-MM-dd'T'HH:mm"
    }

    fun patternSimple(): String {
        return "yyyy-MM-dd"
    }

    fun patternStringWeek(): String {
        return "E"
    }

}