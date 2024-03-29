package com.example.thindie.weather_fetcher.repository

import com.example.thindie.core.network.di.DispatchersIOModule
import com.example.thindie.database.room.WeatherHourlyDao
import com.example.thindie.domain.ForecastAbleRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.weather_fetcher.mappers.map
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

@Singleton
internal class WeatherHourlyRepositoryImpl @Inject constructor(
    private val hourlyDao: WeatherHourlyDao,
    @DispatchersIOModule.IODispatcher private val ioDispatcher: CoroutineDispatcher,
) : ForecastAbleRepository<WeatherHourly> {


    override fun observeForecastAbleList(): Flow<List<WeatherHourly>> {
        return hourlyDao.observeAllWeathersSite().map {
            it.map {
                it.map()
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun deleteForecastAble(place: String) {
        IO {
            hourlyDao.deleteWeatherSite(place)
        }
    }

    override suspend fun rememberChanges(forecastAble: ForecastAble) {
        val hourly = IO {
            hourlyDao.getWeatherSite(
                forecastAble.getSightLatitude().toString().toDouble(),
                forecastAble.getSightLongitude().toString().toDouble()
            )
        }

        deleteForecastAble(hourly.place)
        IO {
            hourlyDao.upsertWeatherSite(hourly.copy(place = forecastAble.getSight()))
        }
    }


    private suspend fun <T> IO(action: suspend () -> T): T {
        return withContext(ioDispatcher) {
            action.invoke()
        }
    }


}