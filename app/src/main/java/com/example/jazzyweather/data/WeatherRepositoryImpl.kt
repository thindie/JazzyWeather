package com.example.jazzyweather.data

import com.example.jazzyweather.data.local.FavoriteWeatherDao
import com.example.jazzyweather.data.remote.WeatherApiService
import com.example.jazzyweather.data.remote.toDTO
import com.example.jazzyweather.di.DispatchersModule
import com.example.jazzyweather.domain.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val favoriteWeatherDao: FavoriteWeatherDao,
    private val weatherApiService: WeatherApiService,
    private val placeDetector: PlaceDetector,
    @DispatchersModule.IODispatcher private val IO: CoroutineDispatcher,
) : JazzyWeatherRepository {
    override fun searchAndSelectLocation(location: String): Flow<Results<List<Possibility>>> {
        return flow {
            val possibility = placeDetector.produce(location)
            delay(1000)
            emit(possibility)
        }
    }

    override suspend fun getFavoriteWeatherLocations(): Results<List<Weather>> =
        withContext(IO) {
            val list = mutableListOf<Weather>()
            favoriteWeatherDao.getAllFavorites().map {
                requestWeather(it.fromDBtoPossibility()).unpackResult()
                    ?.let { weather -> list.add(weather) }
            }
            list.toList().encapsulateResult()
        }


    override suspend fun requestWeather(possibility: Possibility): Results<Weather> {
        val weather = weatherApiService.getWeather(
            latitude = possibility.latitude,
            longitude = possibility.longitude,
            timeZone = possibility.timeZone
        )
        return weather.toDTO().checkAndTransit {
            possibility.combineWith(it, possibility.place)
        }
    }

    override suspend fun addToFavorites(weather: Weather) {
        favoriteWeatherDao.addFavorite(weather.fromDomainToDB())
    }

    override suspend fun deleteFromFavorites(id: String) {
        favoriteWeatherDao.deleteFavorite(id)
    }

    override fun getOfflineWeathers(): Flow<Results<List<WeatherOffline>>> {
        return flow {
            emit(favoriteWeatherDao.getAllFavorites().map {
                it.fromDBtoOffline()
            }.encapsulateResult())
        }
    }

}