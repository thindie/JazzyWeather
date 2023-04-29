package com.example.thindie.database.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
internal interface WeatherRoomDao {
    @Query("SELECT * FROM favoriteWeather ORDER BY place DESC")
    suspend fun getAllWeathersSite(): List<WeatherDbModel>


    @Upsert
    suspend fun upsertWeatherSite(weatherDbModel: WeatherDbModel)

    @Query("DELETE FROM favoriteWeather WHERE place=:id")
    suspend fun deleteWeatherSite(id: String)
}