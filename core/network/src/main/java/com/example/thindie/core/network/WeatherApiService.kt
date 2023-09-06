@file:Suppress("LongParameterList")

package com.example.thindie.core.network

import com.example.thindie.core.network.dto.dailydto.WeatherDailyResponse
import com.example.thindie.core.network.dto.hourlydto.WeatherHourlyResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal const val ENDPOINT = "/v1/forecast"

internal interface WeatherApiService {


    @GET(ENDPOINT)
    suspend fun getDailyWeather(
        @Query(QUERY_PARAM_LATITUDE) latitude: Float,
        @Query(QUERY_PARAM_LONGITUDE) longitude: Float,
        @Query(QUERY_PARAM_DAILY, encoded = true) daily: String = NETWORK_QUERY,
        @Query(QUERY_PARAM_WINDSPEED_UNIT) windSpeed: String = "ms",
        @Query(QUERY_PARAM_TIMEZONE, encoded = true) timeZone: String,
        @Query(QUERY_CURRENT_WEATHER) boolean: Boolean = true,
    ): WeatherDailyResponse

    @GET(ENDPOINT)
    suspend fun getHourlyWeather(
        @Query(QUERY_PARAM_LATITUDE) latitude: Float,
        @Query(QUERY_PARAM_LONGITUDE) longitude: Float,
        @Query(QUERY_PARAM_HOURLY, encoded = true) hourly: String = HOURLY_NETWORK_QUERY,
        @Query(QUERY_PARAM_WINDSPEED_UNIT) windSpeed: String = "ms",
        @Query(QUERY_PARAM_TIMEZONE, encoded = true) timeZone: String,
        @Query(QUERY_CURRENT_WEATHER) boolean: Boolean = true,
    ): WeatherHourlyResponse


}


private const val QUERY_PARAM_LONGITUDE = "longitude"
private const val QUERY_PARAM_LATITUDE = "latitude"
private const val QUERY_CURRENT_WEATHER = "current_weather"
private const val QUERY_PARAM_WINDSPEED_UNIT = "windspeed_unit"
private const val QUERY_PARAM_TIMEZONE = "timezone"
private const val QUERY_PARAM_DAILY = "daily"
private const val QUERY_PARAM_HOURLY = "hourly"
private const val NETWORK_QUERY =
    "weathercode,temperature_2m_max,temperature_2m_min," +
            "apparent_temperature_max,apparent_temperature_min,sunrise,sunset,uv_index_max,precipitation_sum," +
            "rain_sum,showers_sum,snowfall_sum,precipitation_hours,precipitation_probability_max,windspeed_10m_max," +
            "windgusts_10m_max,winddirection_10m_dominant"
private const val HOURLY_NETWORK_QUERY =
    "temperature_2m,apparent_temperature,precipitation," +
            "rain,showers,snowfall,weathercode,visibility,windspeed_10m" +
            ",windgusts_10m"

