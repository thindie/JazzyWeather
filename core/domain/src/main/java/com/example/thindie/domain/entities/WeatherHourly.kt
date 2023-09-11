package com.example.thindie.domain.entities

data class WeatherHourly(
    val place: String,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timeZoneAbbreviation: String,
    val utcOffsetSeconds: Int,
    val apparentTemperature: List<Double>,
    val precipitation: List<Double>,
    val rain: List<Double>,
    val showers: List<Double>,
    val snowfall: List<Double>,
    val temperature2m: List<Double>,
    val time: List<String>,
    val visibility: List<Double>,
    val weatherCode: List<Int>,
    val windGusts10m: List<Double>,
    val windSpeed10m: List<Double>,
) : ForecastAble {
    override fun getSight() = this.place

    override fun getSightLatitude() = this.latitude.toFloat()

    override fun getSightLongitude() = this.longitude.toFloat()

    override fun getTimeZone() = this.timezone

    fun getHourlyForecast(): List<OneHourWeather> {
        val oneHourWeather = mutableListOf<OneHourWeather>()
        time.forEachIndexed { index, hour ->
            if (index < 24) {
                OneHourWeather(
                    place = place,
                    latitude = latitude,
                    longitude = longitude,
                    timezone = timezone,
                    timeZoneAbbreviation = timeZoneAbbreviation,
                    utcOffsetSeconds = utcOffsetSeconds,
                    apparentTemperature = apparentTemperature[index],
                    precipitation = precipitation[index],
                    rain = rain[index],
                    showers = showers[index],
                    snowfall = snowfall[index],
                    temperature2m = temperature2m[index],
                    time = hour,
                    visibility = visibility[index],
                    weatherCode = weatherCode[index],
                    windGusts10m = windGusts10m[index],
                    windSpeed10m = windSpeed10m[index]
                ).apply {
                    oneHourWeather.add(this)
                }
            }
        }
        return oneHourWeather
    }

}