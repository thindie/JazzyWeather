package com.example.jazzyweather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Singleton

@Singleton
@Database(entities = [WeatherDBModel::class], version = 1, exportSchema = false)
abstract class FavoriteDataBase() : RoomDatabase() {
    abstract fun bindFavoriteWeatherDao(): FavoriteWeatherDao
}