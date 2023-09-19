package com.example.thindie.domain

interface TimeRepository {
    fun getToday(): Int
    fun getCurrentHour(date: String): String
    fun getWeekInNumbers(listIso8106: List<String>): List<Int>
    fun getCurrentWeekDay(): String
    fun getCurrentMonth(): String
    fun getCurrentYear(): String

    fun getTimeZoneId(): String

    fun getCurrentHour(): Int

}