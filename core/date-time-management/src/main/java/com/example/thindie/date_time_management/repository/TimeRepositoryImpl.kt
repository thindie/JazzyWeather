package com.example.thindie.date_time_management.repository

import com.example.thindie.domain.TimeRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named

internal class TimeRepositoryImpl @Inject constructor(
    private val calendar: Calendar,
    @Named("week_day") private val weekDay: SimpleDateFormat,
    @Named("month") private val month: SimpleDateFormat,
    @Named("year") private val year: SimpleDateFormat,
) :
    TimeRepository {
    override fun getToday(): Int {
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    override fun getCurrentHour(date: String): Int {
        return calendar.get(Calendar.HOUR_OF_DAY)
    }

    override fun getWeekDayNumber(): List<Int> {
        var currentDay = getToday()
        return buildList {
            repeat(7) {
                this.add(currentDay++)
            }
        }
    }

    override fun getCurrentWeekDay(): String {
        val date = calendar.time
        return weekDay.format(date)
    }

    override fun getCurrentMonth(): String {
        val date = calendar.time
        return month.format(date)
    }

    override fun getCurrentYear(): String {
        val date = calendar.time
        return year.format(date)
    }

    override fun getTimeZoneId(): String {
        return calendar.timeZone.id
    }
}