package com.example.jazzyweather.data.remote

import com.example.jazzyweather.domain.Results
import com.example.jazzyweather.data.remote.utils.CurrentWeather
import com.example.jazzyweather.data.remote.utils.Daily
import com.google.gson.Gson
import com.google.gson.JsonObject

fun JsonObject?.toDTO(): Results<WeatherDTO> {
    var daily: Daily? = null
    var current: CurrentWeather? = null
    this?.entrySet()?.forEach {
        if (it.key == DAILY) {
            daily = Gson().fromJson(it.value, Daily::class.java)
        }
        if (it.key == CURRENT_WEATHER) {
            current = Gson().fromJson(it.value, CurrentWeather::class.java)
        }
    } ?: return Results.Error(Exception("no or bad remote data"))

    if (daily != null && current != null) {
        return Results.Success(
            WeatherDTO(
                temperature = current!!.temperature,
                time = current!!.time,
                weathercode = current!!.weathercode,
                winddirection = current!!.winddirection,
                windspeed = current!!.windspeed,
                apparent_temperature_max = daily!!.apparent_temperature_max,
                apparent_temperature_min = daily!!.apparent_temperature_min,
                precipitation_sum = daily!!.precipitation_sum,
                rain_sum = daily!!.rain_sum,
                showers_sum = daily!!.showers_sum,
                snowfall_sum = daily!!.snowfall_sum,
                sunrise = daily!!.sunrise,
                sunset = daily!!.sunset,
                temperature_2m_max = daily!!.temperature_2m_max,
                temperature_2m_min = daily!!.temperature_2m_min,
                times = daily!!.time,
                weathercodes = daily!!.weathercode,
                windgusts_10m_max = daily!!.windgusts_10m_max,
                windspeed_10m_max = daily!!.windspeed_10m_max,
            )
        )
    }
    return Results.Error(Exception("no or bad remote data"))
}
