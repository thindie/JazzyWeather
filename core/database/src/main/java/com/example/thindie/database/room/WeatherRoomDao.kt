package com.example.thindie.database.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface WeatherRoomDao {
    @Query("SELECT * FROM weather ORDER BY place DESC")
    suspend fun getAllWeathersSite(): List<WeatherDbModel>

    @Query("SELECT * FROM weather WHERE place == :city LIMIT 1")
    suspend fun getWeatherSite(city: String): WeatherDbModel

    @Upsert
    suspend fun upsertWeatherSite(weatherDbModel: WeatherDbModel)

    @Query("DELETE FROM weather WHERE place=:id")
    suspend fun deleteWeatherSite(id: String)
}