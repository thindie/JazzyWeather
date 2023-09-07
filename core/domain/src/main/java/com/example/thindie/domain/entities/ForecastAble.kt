package com.example.thindie.domain.entities

interface ForecastAble {
    fun getSight(): String
    fun getSightLatitude(): Float
    fun getSightLongitude(): Float

    fun getTimeZone(): String

    fun <T> getReified(): T = this as T
}