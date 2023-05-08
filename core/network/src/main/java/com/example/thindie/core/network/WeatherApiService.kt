@file:Suppress("LongParameterList")

package com.example.thindie.core.network

import com.example.thindie.core.network.util.ENDPOINT
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface WeatherApiService {
    @GET(ENDPOINT)
    suspend fun getWeatherJson(
        @Query(QUERY_PARAM_LATITUDE) latitude: Float,
        @Query(QUERY_PARAM_LONGITUDE) longitude: Float,
        @Query(QUERY_PARAM_DAILY, encoded = true) daily: String = NETWORK_QUERY,
        @Query(QUERY_PARAM_WINDSPEED_UNIT) windSpeed: String = "ms",
        @Query(QUERY_PARAM_TIMEZONE, encoded = true) timeZone: String,
        @Query(QUERY_CURRENT_WEATHER) boolean: Boolean = true,
    ): Response<JsonObject>

    @GET(ENDPOINT)
    suspend fun getHourlyWeatherJson(
        @Query(QUERY_PARAM_LATITUDE) latitude: Float,
        @Query(QUERY_PARAM_LONGITUDE) longitude: Float,
        @Query(QUERY_PARAM_HOURLY, encoded = true) hourly: String = HOURLY_NETWORK_QUERY,
        @Query(QUERY_PARAM_WINDSPEED_UNIT) windSpeed: String = "ms",
        @Query(QUERY_PARAM_TIMEZONE, encoded = true) timeZone: String,
        @Query(QUERY_CURRENT_WEATHER) boolean: Boolean = true,
    ): Response<JsonObject>


}


private const val QUERY_PARAM_LONGITUDE = "longitude"
private const val QUERY_PARAM_LATITUDE = "latitude"
private const val QUERY_CURRENT_WEATHER = "current_weather"
private const val QUERY_PARAM_WINDSPEED_UNIT = "windspeed_unit"
private const val QUERY_PARAM_TIMEZONE = "timezone"
private const val QUERY_PARAM_DAILY = "daily"
private const val QUERY_PARAM_HOURLY = "hourly"
private const val NETWORK_QUERY =
    "weathercode,temperature_2m_max,temperature_2m_min,apparent_temperature_max," +
            "apparent_temperature_min,sunrise,sunset,precipitation_sum,rain_sum,showers_sum," +
            "snowfall_sum,windspeed_10m_max,windgusts_10m_max"
private const val HOURLY_NETWORK_QUERY = "temperature_2m,precipitation,weathercode,windspeed_10m"

