package com.example.jazzyweather.data

import com.example.jazzyweather.data.local.FavoriteWeatherDao
import com.example.jazzyweather.data.local.possibilities.PossibilitiesDao
import com.example.jazzyweather.data.remote.WeatherApiService
import com.example.jazzyweather.data.remote.toDTO
import com.example.jazzyweather.data.remote.toHourlyDTO
import com.example.jazzyweather.di.DispatchersModule
import com.example.jazzyweather.domain.*
import com.example.jazzyweather.domain.abstractions.Results
import com.example.jazzyweather.domain.abstractions.checkAndTransit
import com.example.jazzyweather.domain.abstractions.encapsulateResult
import com.example.jazzyweather.domain.abstractions.unpackResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val favoriteWeatherDao: FavoriteWeatherDao,
    private val weatherApiService: WeatherApiService,
    private val placeDetector: PlaceDetector,
    private val possibilitiesDao: PossibilitiesDao,
    @DispatchersModule.IODispatcher private val IO: CoroutineDispatcher,
) : JazzyWeatherRepository {
    override fun searchAndSelectLocation(location: String): Flow<Results<List<Possibility>>> {
        return flow {
            placeDetector.produce(location).collect {
                emit(it)
            }
        }
    }

    override suspend fun getSavedPossibilities(): Results<List<Possibility>> {
        val list = mutableListOf<Possibility>()
        possibilitiesDao.getSavedPossibilities().asFlow().collect {
            list.add(it.toModel())
        }
        if (list.isEmpty()) delay(20)
        return list.encapsulateResult()
    }

    override suspend fun getHourlyWeather(possibility: Possibility): Results<WeatherHourly> {
        val hourlyJsonObj = weatherApiService.getHourlyWeather(
            latitude = possibility.latitude,
            longitude = possibility.longitude,
            timeZone = possibility.timeZone
        )
        return hourlyJsonObj.toHourlyDTO(possibility).checkAndTransit {
            it.fromDTOtoModel()
        }
    }


    override suspend fun savePossibilities(list: List<Possibility>) {
        withContext(IO) {
            list.forEach {
                possibilitiesDao.insertPossibility(it.toDBModel())
            }
        }
    }

    override suspend fun getFavoriteWeatherLocations(): Results<List<Weather>> = withContext(IO) {
        val list = mutableListOf<Weather>()
        favoriteWeatherDao.getAllFavorites().map {
            requestWeather(it.fromDBtoPossibility()).unpackResult()
                ?.let { weather: Weather -> list.add(weather) }
        }
        list.toList().encapsulateResult()
    }


    override suspend fun requestWeather(possibility: Possibility) = withContext(IO) {
        savePossibilities(listOf(possibility))
        val weather = weatherApiService.getWeather(
            latitude = possibility.latitude,
            longitude = possibility.longitude,
            timeZone = possibility.timeZone
        )
        weather.toDTO().checkAndTransit {
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