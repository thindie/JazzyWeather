package com.example.thindie.database.di

import android.app.Application
import androidx.room.Room
import com.example.thindie.database.room.WeatherRoomDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppDataBaseModule {
    @Provides
    @Singleton
    fun provideDataBase(application: Application): WeatherDataBase {
        return Room.databaseBuilder(
            context = application,
            klass = WeatherDataBase::class.java,
            name = DB_NAME
        ).build()
    }


}

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    fun bindFavoriteWeatherDao(database: WeatherDataBase): WeatherRoomDao {
        return database.bindWeatherRoomDao()
    }
}
private const val DB_NAME = "WeatherRoom.db"
