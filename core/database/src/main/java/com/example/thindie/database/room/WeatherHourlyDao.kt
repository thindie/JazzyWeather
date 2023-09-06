package com.example.thindie.database.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface WeatherHourlyDao {
    @Query("SELECT * FROM weather_hourly ORDER BY place DESC")
    suspend fun getAllWeathersSite(): List<WeatherHourlyDbModel>

    @Query("SELECT * FROM weather_hourly WHERE place LIKE :place LIMIT 1")
    suspend fun getWeatherSite(place: String): WeatherHourlyDbModel

    @Upsert
    suspend fun upsertWeatherSite(weatherDailyDbModel: WeatherHourlyDbModel)

    @Delete
    suspend fun deleteWeatherSite(weatherDailyDbModel: WeatherHourlyDbModel)
}