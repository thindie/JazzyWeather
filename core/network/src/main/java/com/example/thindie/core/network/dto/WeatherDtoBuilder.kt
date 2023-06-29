package com.example.thindie.core.network.dto

import com.example.thindie.core.network.generated.CurrentWeather
import com.example.thindie.core.network.generated.Daily
import com.example.thindie.core.network.generated.WeatherJsonDto
import com.example.thindie.core.network.util.parseJsonTo
import com.google.gson.JsonObject
import retrofit2.Response

internal class WeatherDtoBuilder private constructor(
    private val currentWeather: CurrentWeather,
    private val daily: Daily,
    private val latitude: Double,
    private val longitude: Double,
) {
    private fun dto() = WeatherDto(
        latitude = latitude,
        longitude = longitude,
        temperature = currentWeather.temperature,
        time = currentWeather.time,
        weatherCode = currentWeather.weathercode,
        windDirection = currentWeather.winddirection,
        windSpeed = currentWeather.windspeed,
        apparentTemperatureMax = daily.apparent_temperature_max,
        apparentTemperatureMin = daily.apparent_temperature_min,
        precipitationSum = daily.precipitation_sum,
        rainSum = daily.rain_sum,
        showersSum = daily.showers_sum,
        snowfallSum = daily.snowfall_sum,
        sunrise = daily.sunrise,
        sunset = daily.sunset,
        temperatureTwoMetersMax = daily.temperature_2m_max,
        temperatureTwoMetersMin = daily.temperature_2m_min,
        times = daily.time,
        weatherCodes = daily.weathercode,
        windgustsTenMetersMin = daily.windgusts_10m_max,
        windspeedTenMetersMax = daily.windspeed_10m_max,
    )

    companion object {
        suspend operator fun invoke(getRawJson: suspend () -> Response<JsonObject>): Result<WeatherDto> {
            return kotlin.runCatching {
                val weatherRaw = getRawJson().body().toString()
                val weatherDto = weatherRaw.parseJsonTo<WeatherJsonDto>()
                WeatherDtoBuilder(
                    weatherDto.current_weather,
                    weatherDto.daily,
                    weatherDto.latitude,
                    weatherDto.longitude
                ).dto()
            }
        }
    }
}
