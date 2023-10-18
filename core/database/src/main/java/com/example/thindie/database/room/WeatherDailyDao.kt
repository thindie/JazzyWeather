package com.example.thindie.database.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface WeatherDailyDao {
    @Query("SELECT * FROM weather_daily ORDER BY place DESC")
    suspend fun getAllWeathersSite(): List<WeatherDailyDbModel>

    @Query("SELECT * FROM weather_daily WHERE place LIKE :place LIMIT 1")
    suspend fun getWeatherSite(place: String): WeatherDailyDbModel

    @Query("SELECT * FROM weather_daily WHERE latitude == :latitude AND longitude == :longitude LIMIT 1")
    suspend fun getWeatherSite(latitude: Double, longitude: Double): WeatherDailyDbModel

    @Upsert
    suspend fun upsertWeatherSite(weatherDailyDbModel: WeatherDailyDbModel)

    @Delete
    suspend fun deleteWeatherSite(weatherDailyDbModel: WeatherDailyDbModel)

    @Query("DELETE FROM weather_daily WHERE place ==:place")
    fun deleteWeatherSite(place: String)
}