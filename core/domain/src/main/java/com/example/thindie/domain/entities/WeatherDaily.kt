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

    fun getDailyForecast(): List<OneDayWeather> {
        val oneDayWeather = mutableListOf<OneDayWeather>()
        time.forEachIndexed { index, hour ->
            if (index < 7) {
                OneDayWeather(
                    place = place,
                    latitude = latitude,
                    longitude = longitude,
                    timezone = timezone,
                    timeZoneAbbreviation = timeZoneAbbreviation,
                    utcOffsetSeconds = utcOffsetSeconds,
                    precipitationHours = precipitationHours[index],
                    precipitationProbabilityMax = precipitationProbabilityMax[index],
                    precipitationSum = precipitationSum[index],
                    rainSum = rainSum[index],
                    showersSum = showersSum[index],
                    snowfallSum = snowfallSum[index],
                    time = hour,
                    uvIndexMax = uvIndexMax[index],
                    windDirection10mDominant = windDirection10mDominant[index],
                    windGusts10mMax = windGusts10mMax[index],
                    windSpeed10mMax = windSpeed10mMax[index],
                    elevation = elevation,
                    weatherCode = weatherCode[index],
                    sunset = sunset[index],
                    sunrise = sunrise[index],
                    apparentTemperatureMax = apparentTemperatureMax[index],
                    apparentTemperatureMin = apparentTemperatureMin[index]
                ).apply {
                    oneDayWeather.add(this)
                }
            }
        }
        return oneDayWeather
    }

    private fun List<Double>.getAverage(min: List<Double>): List<Double> {
        val resultList = mutableListOf<Double>()
        if (this.size == min.size) {
            forEachIndexed { index, value ->
                resultList.add((value + min[index]).div(2))
            }
        } else {
            resultList.addAll(this)
        }
        return resultList
    }

    val temperature
        get() = apparentTemperatureMax.getAverage(apparentTemperatureMin)
}
