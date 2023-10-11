package com.example.thindie.date_time_management.repository


import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class WeekDaysProvider @Inject constructor(
    @Named("stringDay") private val dayOfWeek: SimpleDateFormat,
    private val calendar: Calendar,
) {

    fun returnWeekList(): List<String> {
        val date: Date = calendar.time
        val innerCalendar = calendar

        return buildList {
            repeat(DAYS_IN_WEEK) {
                val inMillis = date.time.plus(it * MILLIS_IN_DAY)
                innerCalendar.timeInMillis = inMillis
                add(
                    dayOfWeek.format(innerCalendar.time)
                )
            }
        }
    }

    companion object {
        const val MILLIS_IN_DAY = 86400000L
        const val DAYS_IN_WEEK = 7
    }

}