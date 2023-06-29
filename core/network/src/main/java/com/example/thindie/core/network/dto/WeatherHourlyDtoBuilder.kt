package com.example.thindie.core.network.dto

import com.example.thindie.core.network.generated.hourly.WeatherHourlyRawDto
import com.example.thindie.core.network.util.parseJsonTo
import com.google.gson.JsonObject
import retrofit2.Response

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
        suspend operator fun invoke(getRawJson: suspend () -> Response<JsonObject>): Result<WeatherHourlyDto> {
            return kotlin.runCatching {
                val weatherRaw = getRawJson().body().toString()
                val weatherHourlyDto = weatherRaw.parseJsonTo<WeatherHourlyRawDto>()
                WeatherHourlyDtoBuilder(
                    latitude = weatherHourlyDto.latitude,
                    longitude = weatherHourlyDto.longitude,
                    precipitation = weatherHourlyDto.hourly.precipitation,
                    temperatureTwoMeters = weatherHourlyDto.hourly.temperature_2m,
                    time = weatherHourlyDto.hourly.time,
                    weatherCode = weatherHourlyDto.hourly.weathercode,
                    windspeedTenMeters = weatherHourlyDto.hourly.windspeed_10m,
                ).hourlyDto()
            }

        }
    }
}