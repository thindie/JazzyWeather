package com.example.thindie.weather_fetcher.repository

import com.example.thindie.core.network.di.DispatchersIOModule
import com.example.thindie.database.room.WeatherDailyDao
import com.example.thindie.domain.ForecastAbleRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.weather_fetcher.WTF
import com.example.thindie.weather_fetcher.mappers.map
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

@Singleton
internal class WeatherDailyRepositoryImpl @Inject constructor(
    private val dailyDao: WeatherDailyDao,
    @DispatchersIOModule.IODispatcher private val ioDispatcher: CoroutineDispatcher,
) : ForecastAbleRepository<WeatherDaily> {


    override fun observeForecastAbleList(): Flow<List<WeatherDaily>> {
        return dailyDao.observeAllWeathersSite().map {
            it.map { it.map() }
        }.flowOn(ioDispatcher)
    }

    override suspend fun deleteForecastAble(place: String) {
        IO {
            dailyDao.deleteWeatherSite(place)
        }

    }

    override suspend fun rememberChanges(forecastAble: ForecastAble) {

        val daily = IO {
            dailyDao.getWeatherSite(
                latitude = forecastAble.getSightLatitude().toString().toDouble(),
                longitude = forecastAble.getSightLongitude().toString().toDouble()
            )
        }
        //   Log.d("SERVICE_TAG", daily.place)
        try {
            deleteForecastAble(daily.place)
        } catch (e: Exception) {
            WTF { e.toString() }
        }

        IO {
            dailyDao.upsertWeatherSite(daily.copy(place = forecastAble.getSight()))
        }

    }

    private suspend fun <T> IO(action: suspend () -> T): T {
        return withContext(ioDispatcher) {
            action.invoke()
        }
    }

}