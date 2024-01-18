package com.example.thindie.domain.entities

data class WeatherDaily(
    val place: String,
    val latitude: Double,
    val longitude: Double,
    val elevation: Double,
    val timezone: String,
    val timeZoneAbbreviation: String,
    val utcOffsetSeconds: Int,
    val precipitationHours: List<Double>,
    val precipitationProbabilityMax: List<Int>,
    val precipitationSum: List<Double>,
    val rainSum: List<Double>,
    val showersSum: List<Double>,
    val snowfallSum: List<Double>,
    val time: List<String>,
    val uvIndexMax: List<Double>,
    val windDirection10mDominant: List<Int>,
    val windGusts10mMax: List<Double>,
    val windSpeed10mMax: List<Double>,
    val weatherCode: List<Int>,
    val sunset: List<String>,
    val sunrise: List<String>,
    val apparentTemperatureMax: List<Double>,
    val apparentTemperatureMin: List<Double>,
) : ForecastAble {
    override fun getSight() = this.place

    override fun getSightLatitude() = this.latitude.toFloat()

    override fun getSightLongitude() = this.longitude.toFloat()

    override fun getTimeZone() = this.timezone

}
