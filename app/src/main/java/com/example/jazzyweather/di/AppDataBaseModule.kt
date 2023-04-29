package com.example.jazzyweather.di

import android.app.Application
import androidx.room.Room
import com.example.jazzyweather.data.local.FavoriteDataBase
import com.example.jazzyweather.data.local.FavoriteWeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDataBaseModule {
    @Provides
    @Singleton
    fun provideDataBase(application: Application): FavoriteDataBase {
        return Room.databaseBuilder(
            context = application,
            klass = FavoriteDataBase::class.java,
            name = DB_NAME
        ).fallbackToDestructiveMigration().build()
    }


}

@Module
@InstallIn(SingletonComponent::class)
class DaoModule() {
    @Provides
    fun bindFavoriteWeatherDao(favoriteDataBase: FavoriteDataBase): FavoriteWeatherDao {
        return favoriteDataBase.bindFavoriteWeatherDao()
    }


}

private const val DB_NAME = "favoriteWeather.db"
