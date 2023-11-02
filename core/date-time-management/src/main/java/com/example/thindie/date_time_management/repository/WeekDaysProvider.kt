package com.example.thindie.date_time_management.repository


import android.util.Log
import com.example.thindie.domain.entities.ForecastDay
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class WeekDaysProvider @Inject constructor(
    @Named("stringDay") private val dayOfWeek: SimpleDateFormat,
    @Named("simpleDate") private val simpleDate: SimpleDateFormat,
    @Named("week_day") private val weekDayFormat: SimpleDateFormat,
    private val calendar: Calendar,
) {

    fun returnWeekList(): List<ForecastDay> {
        val timeInMillis = calendar.timeInMillis

        return buildList {
            repeat(DAYS_IN_WEEK) {
                val inMillis = timeInMillis.plus(it * MILLIS_IN_DAY)
                add(
                    ForecastDay(
                        weekDay = dayOfWeek.format(inMillis),
                        inMillis = inMillis
                    )
                )
            }
        }
    }


    fun getSimpleDateFromMillis(millis: Long): String {
        return simpleDate.format(Date(millis))
    }

    fun getWeekInNumbers(listIso8106: List<String>): List<Int> {
        return listIso8106.map {
            simpleDate.parse(it)
        }.map {
            if (it != null) {
                weekDayFormat.format(it).toInt()
            } else 0
        }.take(7)
    }

    companion object {
        const val MILLIS_IN_DAY = 86400000L
        const val DAYS_IN_WEEK = 7
    }

}