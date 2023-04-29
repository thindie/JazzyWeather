package com.example.thindie.core.network.dto

import com.example.thindie.core.network.generated.hourly.WeatherHourlyRawDto
import com.example.thindie.core.network.util.parseJsonTo

@Suppress("LongParameterList")
internal class WeatherHourlyDtoBuilder private constructor(
    private val latitude: Double,
    private val longitude: Double,
    private val precipitation: List<Double>,
    private val temperatureTwoMeters: List<Double>,
    private val time: List<String>,
    private val weatherCode: List<Int>,
    private val windspeedTenMeters: List<Double>,
) {
    private fun hourlyDto() = WeatherHourlyDto(
        latitude = latitude,
        longitude = longitude,
        precipitation = precipitation,
        temperatureTwoMeters = temperatureTwoMeters,
        time = time,
        weatherCode = weatherCode,
        windspeedTenMeters = windspeedTenMeters,
    )

    companion object {
        suspend operator fun invoke(getRawJson: suspend () -> String) = try {
            val weatherRaw = getRawJson()
                .parseJsonTo<WeatherHourlyRawDto>()
            WeatherHourlyDtoBuilder(
                latitude = weatherRaw.latitude,
                longitude = weatherRaw.longitude,
                precipitation = weatherRaw.hourly.precipitation,
                temperatureTwoMeters = weatherRaw.hourly.temperature_2m,
                time = weatherRaw.hourly.time,
                weatherCode = weatherRaw.hourly.weathercode,
                windspeedTenMeters = weatherRaw.hourly.windspeed_10m,
            ).hourlyDto()
        } catch (e: IllegalStateException) {
            e.message
            null
        }
    }
}