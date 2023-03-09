package com.example.jazzyweather.di

import android.app.Application
import androidx.room.Room
import com.example.jazzyweather.data.local.FavoriteDataBase
import com.example.jazzyweather.data.local.FavoriteWeatherDao
import com.example.jazzyweather.data.local.possibilities.PossibilitiesDao
import com.example.jazzyweather.data.local.possibilities.PossibilitiesDataBase
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


    @Provides
    @Singleton
    fun providePossibilityDataBase(application: Application): PossibilitiesDataBase {
        return Room.databaseBuilder(
            context = application,
            klass = PossibilitiesDataBase::class.java,
            name = DB_NAME_POSSIBILITY
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

    @Provides
    fun bindPossibilityWeatherDao(possibilitiesDataBase: PossibilitiesDataBase): PossibilitiesDao {
        return possibilitiesDataBase.bindPossibilitiesDao()
    }

}

private const val DB_NAME = "favoriteWeather.db"
private const val DB_NAME_POSSIBILITY = "possibility.db"