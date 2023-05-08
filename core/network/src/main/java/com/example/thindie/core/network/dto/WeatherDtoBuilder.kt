package com.example.thindie.core.network.dto

import android.util.Log
import com.example.thindie.core.network.generated.CurrentWeather
import com.example.thindie.core.network.generated.Daily
import com.example.thindie.core.network.generated.WeatherJsonDto
import com.example.thindie.core.network.util.parseJsonTo

internal class WeatherDtoBuilder private constructor(
    private val currentWeather: CurrentWeather,
    private val daily: Daily,
    private val latitude: Double,
    private val longitude: Double
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
        suspend operator fun invoke(getRawJson: suspend () -> String) = try {
            val weatherRaw = getRawJson()
                .parseJsonTo<WeatherJsonDto>()
            WeatherDtoBuilder(
                weatherRaw.current_weather,
                weatherRaw.daily,
                weatherRaw.latitude,
                weatherRaw.longitude
            ).dto()
        } catch (e: IllegalStateException) {
            Log.d("SERVICE_TAG_temperature", e.message.toString())
             throw e
        }
    }
}
