package com.example.thindie.database.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherHourlyDao {
    @Query("SELECT * FROM weather_hourly ORDER BY place DESC")
    fun observeAllWeathersSite(): Flow<List<WeatherHourlyDbModel>>

    @Query("SELECT * FROM weather_hourly ORDER BY place DESC")
    fun getAllWeathersSite(): List<WeatherHourlyDbModel>

    @Query("SELECT * FROM weather_hourly WHERE place LIKE :place LIMIT 1")
    fun getWeatherSite(place: String): WeatherHourlyDbModel

    @Query("SELECT * FROM weather_hourly WHERE latitude = :latitude AND longitude = :longitude LIMIT 1")
    fun getWeatherSite(latitude: Double, longitude: Double): WeatherHourlyDbModel

    @Upsert
    fun upsertWeatherSite(weatherDailyDbModel: WeatherHourlyDbModel)

    @Delete
    fun deleteWeatherSite(weatherDailyDbModel: WeatherHourlyDbModel)

    @Query("DELETE FROM weather_hourly WHERE place ==:place")
    fun deleteWeatherSite(place: String)

    @Query("SELECT (SELECT COUNT(*) FROM weather_hourly) == 0")
    fun isEmpty(): Flow<Boolean>
}