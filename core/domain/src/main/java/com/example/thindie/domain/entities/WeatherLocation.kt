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
) : ForecastAble {
    override fun getPlace() = this.city

    override fun getLatitude() = try {
        this.latitude.toDouble()
    } catch (e: NumberFormatException) {
        0.0
    }

    override fun getLongitude() = try {
        this.latitude.toDouble()
    } catch (e: NumberFormatException) {
        0.0
    }

}