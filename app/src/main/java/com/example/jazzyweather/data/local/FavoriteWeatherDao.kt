package com.example.jazzyweather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteWeatherDao {
    @Query("SELECT * FROM favoriteWeather ORDER BY place DESC")
    suspend fun getAllFavorites(): List<WeatherDBModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(weatherDBModel: WeatherDBModel)

    @Query("DELETE FROM favoriteWeather WHERE place=:id")
    suspend fun deleteFavorite(id: String)
}