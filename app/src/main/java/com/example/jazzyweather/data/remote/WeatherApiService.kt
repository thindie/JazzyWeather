package com.example.jazzyweather.data.remote

import com.example.jazzyweather.data.remote.utils.Daily
import com.example.jazzyweather.di.ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET(ENDPOINT)
    suspend fun getWeather(
        @Query(QUERY_PARAM_LONGITUDE) longitude: String,
        @Query(QUERY_PARAM_LATITUDE) latitude: String,
        @Query(QUERY_PARAM_DAILY, encoded = true) daily: String = queryBuilder(
            prepareList(
                string
            )
        ),
        @Query(QUERY_PARAM_WINDSPEED_UNIT) windSpeed: String = "ms",
        @Query(QUERY_PARAM_TIMEZONE) timeZone: String,
    ): Daily
}

private const val QUERY_PARAM_LONGITUDE = "longitude"
private const val QUERY_PARAM_LATITUDE = "longitude"
private const val QUERY_PARAM_WINDSPEED_UNIT = "windspeed_unit"
private const val QUERY_PARAM_TIMEZONE = "timezone"
private const val QUERY_PARAM_DAILY = "daily"

private const val COMMA = ","
private const val NETWORK_QUIERY = "%s${COMMA}"


private val string =
    "weathercode,temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,sunrise,sunset,precipitation_sum,rain_sum,showers_sum,snowfall_sum,windspeed_10m_max,windgusts_10m_max"

private fun queryBuilder(list: List<String>) = run {
    var string = ""
    list.forEach {
        string += String.format(NETWORK_QUIERY, it)
    }
    string
}

private val prepareList: (String) -> List<String> = {
    it.split(",")
}