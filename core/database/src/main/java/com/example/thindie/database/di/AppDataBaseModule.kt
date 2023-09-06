package com.example.thindie.database.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppDataBaseModule {
    private const val DB_NAME = "WeatherRoom.db"

    @Provides
    @Singleton
    fun provideDataBase(application: Application): WeatherDataBase {
        val dataBase by lazy {
            Room.databaseBuilder(
                context = application,
                klass = WeatherDataBase::class.java,
                name = DB_NAME
            )
                .build()
        }

        return dataBase
    }
}




