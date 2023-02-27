package com.example.jazzyweather.data.remote

import com.example.jazzyweather.di.ENDPOINT
import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET(ENDPOINT)
    suspend fun getWeather(
        @Query(QUERY_PARAM_LATITUDE) latitude: Float,
        @Query(QUERY_PARAM_LONGITUDE) longitude: Float,
        @Query(QUERY_PARAM_DAILY, encoded = true) daily: String = NETWORK_QUERY,
        @Query(QUERY_PARAM_WINDSPEED_UNIT) windSpeed: String = "ms",
        @Query(QUERY_PARAM_TIMEZONE, encoded = true) timeZone: String,
        @Query(QUERY_CURRENT_WEATHER) boolean: Boolean = true,
    ): JsonObject?
}

private const val QUERY_PARAM_LONGITUDE = "longitude"
private const val QUERY_PARAM_LATITUDE = "latitude"
private const val QUERY_CURRENT_WEATHER = "current_weather"
private const val QUERY_PARAM_WINDSPEED_UNIT = "windspeed_unit"
private const val QUERY_PARAM_TIMEZONE = "timezone"
private const val QUERY_PARAM_DAILY = "daily"
private const val NETWORK_QUERY =
    "weathercode,temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,sunrise,sunset,precipitation_sum,rain_sum,showers_sum,snowfall_sum,windspeed_10m_max,windgusts_10m_max"

const val DAILY = "daily"
const val CURRENT_WEATHER = "current_weather"

private const val COMMA = ","
private fun queryBuilder(list: List<String>) = run {
    var string = ""
    list.forEach {
        string += String.format(NETWORK_QUERY, it)
    }
    string
}

