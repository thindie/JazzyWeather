package com.example.thindie.database.di

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.thindie.database.room.DoubleListTypeConverter
import com.example.thindie.database.room.IntListTypeConverter
import com.example.thindie.database.room.PinnedWeatherDao
import com.example.thindie.database.room.PinnedWeatherDbModel
import com.example.thindie.database.room.StringListTypeConverter
import com.example.thindie.database.room.WeatherDbModel
import com.example.thindie.database.room.WeatherRoomDao
import javax.inject.Singleton

@Singleton
@Database(
    entities = [WeatherDbModel::class, PinnedWeatherDbModel::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    StringListTypeConverter::class,
    DoubleListTypeConverter::class,
    IntListTypeConverter::class
)
internal abstract class WeatherDataBase : RoomDatabase() {
    abstract fun bindWeatherRoomDao(): WeatherRoomDao
    abstract fun getPinnedWeatherDao(): PinnedWeatherDao

}