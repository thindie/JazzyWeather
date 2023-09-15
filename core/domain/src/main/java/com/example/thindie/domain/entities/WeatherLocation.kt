package com.example.thindie.domain.entities

data class WeatherLocation(
    val adminName: String,
    val capital: String,
    val city: String,
    val country: String,
    val iso2: String,
    val latitude: String,
    val longitude: String,
    val population: String,
    val populationProper: String,
    val timezone: String,
) : ForecastAble {
    override fun getSight() = this.city

    override fun getSightLatitude() = try {
        this.latitude.toFloat()
    } catch (e: NumberFormatException) {
        0.0F
    }

    override fun getSightLongitude() = try {
        this.longitude.toFloat()
    } catch (e: NumberFormatException) {
        0.0F
    }

    override fun getTimeZone(): String {
        return timezone
    }

}