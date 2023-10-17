package com.example.thindie.weather_fetcher.repository

import com.example.thindie.core.localrawresources.di.DispatchersIOModule
import com.example.thindie.database.room.WeatherDailyDao
import com.example.thindie.database.room.WeatherDailyDbModel
import com.example.thindie.database.room.WeatherHourlyDao
import com.example.thindie.database.room.WeatherHourlyDbModel
import com.example.thindie.domain.WeatherRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.weather_fetcher.mappers.map
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext


class ReserveDataBus @Inject constructor(
    private val daoHourly: WeatherHourlyDao,
    private val daoDaily: WeatherDailyDao,
    @DispatchersIOModule.IODispatcher private val ioDispatcher: CoroutineDispatcher,
) : WeatherRepository {
    override suspend fun getDailyWeather(forecastAble: ForecastAble): Result<WeatherDaily> {
        return dispatched { Result.success(daoDaily.getWeatherSite(forecastAble.getSight()).map()) }
    }

    override suspend fun getDailyWeatherOnConcreteDate(
        simpleIso8106: String,
        forecastAble: ForecastAble,
    ): Result<WeatherHourly> {
        return Result.failure(error(""))
    }


    override suspend fun getHourlyWeather(forecastAble: ForecastAble): Result<WeatherHourly> {
        return dispatched {
            Result.success(
                daoHourly.getWeatherSite(forecastAble.getSight()).map()
            )
        }
    }

    override suspend fun getWeatherDailyList(): Result<List<WeatherDaily>> {
        return dispatched { Result.success(daoDaily.getAllWeathersSite().map { it.map() }) }
    }

    override suspend fun requestWeatherHourly() {}

    override fun observeWeatherHourlyList(): Flow<List<WeatherHourly>> {
        return daoHourly.observeAllWeathersSite().map { it.map { it.map() } }
    }


    override suspend fun deleteWeather(place: String) {
        dispatched {
            daoDaily.deleteWeatherSite(daoDaily.getWeatherSite(place))
            daoHourly.deleteWeatherSite(daoHourly.getWeatherSite(place))
        }
    }

    override suspend fun rememberWeatherLocation(forecastAble: ForecastAble) {


        val weatherDaily = dispatched { getDailyDbModelByCoordinates(forecastAble) }
        val weatherHourly = dispatched { getHourlyDbModelByCoordinates(forecastAble) }

        if (weatherDaily != null) {
            deleteDaily(weatherDaily)
            dispatched { reNewDaily(forecastAble, weatherDaily) }
        }

        if (weatherHourly != null) {
            deleteHourly(weatherHourly)
            dispatched { reNewHourly(forecastAble, weatherHourly) }
        }

    }

    private suspend fun getHourlyDbModelByCoordinates(
        forecastAble: ForecastAble,
    ): WeatherHourlyDbModel? {
        return try {
            dispatched {
                daoHourly.getWeatherSite(
                    forecastAble.getSightLatitude().toDouble(),
                    forecastAble.getSightLongitude().toDouble()
                )
            }
        } catch (_: Exception) {
            null
        }
    }

    private suspend fun getDailyDbModelByCoordinates(
        forecastAble: ForecastAble,
    ): WeatherDailyDbModel? {
        return dispatched {
            try {
                daoDaily.getWeatherSite(
                    forecastAble.getSightLatitude().toDouble(),
                    forecastAble.getSightLongitude().toDouble()
                )
            } catch (_: Exception) {
                null
            }
        }
    }

    private suspend fun deleteHourly(dbModel: WeatherHourlyDbModel?) {
        try {
            if (dbModel != null) {
                dispatched { daoHourly.deleteWeatherSite(dbModel) }
            }
        } catch (_: Exception) {
        }
    }

    private suspend fun deleteDaily(dbModel: WeatherDailyDbModel?) {
        if (dbModel != null) {
            dispatched { daoDaily.deleteWeatherSite(dbModel) }
        }
    }

    private suspend fun reNewDaily(
        forecastAble: ForecastAble,
        weatherDailyDbModel: WeatherDailyDbModel,
    ) {
        dispatched { daoDaily.upsertWeatherSite(weatherDailyDbModel.copy(forecastAble.getSight())) }

    }

    private suspend fun reNewHourly(
        forecastAble: ForecastAble,
        weatherHourlyDbModel: WeatherHourlyDbModel,
    ) {
        dispatched { daoHourly.upsertWeatherSite(weatherHourlyDbModel.copy(forecastAble.getSight())) }
    }

    private suspend fun <T> dispatched(action: suspend () -> T): T {
        return withContext(ioDispatcher) {
            action.invoke()
        }
    }
}