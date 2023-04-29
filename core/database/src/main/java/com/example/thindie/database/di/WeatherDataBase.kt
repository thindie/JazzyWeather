package com.example.thindie.database.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.thindie.database.room.WeatherDbModel
import com.example.thindie.database.room.WeatherRoomDao
import javax.inject.Singleton

@Singleton
@Database(entities = [WeatherDbModel::class], version = 1, exportSchema = false)
abstract class WeatherDataBase : RoomDatabase() {
    abstract fun bindWeatherRoomDao(): WeatherRoomDao
}