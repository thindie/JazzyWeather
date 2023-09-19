package com.example.thindie.database.di

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.thindie.database.typeconverters.DoubleListTypeConverter
import com.example.thindie.database.typeconverters.IntListTypeConverter
import com.example.thindie.database.typeconverters.StringListTypeConverter
import com.example.thindie.database.room.WeatherDailyDbModel
import com.example.thindie.database.room.WeatherDailyDao
import com.example.thindie.database.room.WeatherHourlyDao
import com.example.thindie.database.room.WeatherHourlyDbModel
import javax.inject.Singleton

@Singleton
@Database(
    entities = [WeatherDailyDbModel::class, WeatherHourlyDbModel::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(
    StringListTypeConverter::class,
    DoubleListTypeConverter::class,
    IntListTypeConverter::class
)
internal abstract class WeatherDataBase : RoomDatabase() {
    abstract fun weatherDailyInstance(): WeatherDailyDao
    abstract fun weatherHourlyInstance(): WeatherHourlyDao
}