package com.example.thindie.domain.entities

interface ForecastAble {
    fun getPlace(): String
    fun getLatitude(): Double
    fun getLongitude(): Double

    fun <T> getReified(): T = this as T
}