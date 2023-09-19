package com.example.thindie.date_time_management.repository

import com.example.thindie.domain.TimeRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
internal class TimeRepositoryImpl @Inject constructor(
    private val calendar: Calendar,
    @Named("week_day") private val weekDayFormat: SimpleDateFormat,
    @Named("month") private val monthFormat: SimpleDateFormat,
    @Named("year") private val yearFormat: SimpleDateFormat,
    @Named("hour") private val hourFormat: SimpleDateFormat,
    @Named("ISO8601") private val isoFormat: SimpleDateFormat,
    @Named("simpleDate") private val simpleDate: SimpleDateFormat,
) :
    TimeRepository {
    override fun getToday(): Int {
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    override fun getCurrentHour(date: String): String {
        hourFormat.timeZone = calendar.timeZone
        val parseDate = try {
            isoFormat.parse(date)
        } catch (e: Exception) {
            null
        }

        return hourFormat.format(parseDate ?: "??:??")
    }

    override fun getCurrentHour(): Int {
        return calendar.get(Calendar.HOUR_OF_DAY)
    }

    override fun getWeekInNumbers(listIso8106: List<String>): List<Int> {
        return listIso8106.map {
            simpleDate.parse(it)
        }.map {
            if (it != null) {
                weekDayFormat.format(it).toInt()
            } else 0
        }.take(7)
    }

    override fun getCurrentWeekDay(): String {
        val date = calendar.time
        return weekDayFormat.format(date)
    }

    override fun getCurrentMonth(): String {
        val date = calendar.time
        return monthFormat.format(date)
    }

    override fun getCurrentYear(): String {
        val date = calendar.time
        return yearFormat.format(date)
    }

    override fun getTimeZoneId(): String {
        return calendar.timeZone.id
    }
}