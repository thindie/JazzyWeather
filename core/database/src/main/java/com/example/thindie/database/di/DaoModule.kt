package com.example.thindie.database.di

import com.example.thindie.database.room.WeatherDailyDao
import com.example.thindie.database.room.WeatherHourlyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    fun provideDailyDao(database: WeatherDataBase): WeatherDailyDao {
        return database.weatherDailyInstance()
    }

    @Provides
    fun provideHourlyDao(dataBase: WeatherDataBase): WeatherHourlyDao {
        return dataBase.weatherHourlyInstance()
    }
}