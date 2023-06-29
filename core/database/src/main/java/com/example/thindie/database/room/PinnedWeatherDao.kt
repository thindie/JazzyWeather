package com.example.thindie.database.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PinnedWeatherDao {
    @Query("SELECT * FROM savedWeather ORDER BY place DESC")
     fun getAllWeather(): Flow<List<PinnedWeatherDbModel>>

    @Query("SELECT * FROM savedWeather WHERE place == :city LIMIT 1")
    suspend fun getWeather (city: String): PinnedWeatherDbModel

    @Upsert
    suspend fun upsertWeather(weatherDbModel: PinnedWeatherDbModel)

    @Query("DELETE FROM savedWeather WHERE place=:place")
    suspend fun deleteWeather(place: String)
}