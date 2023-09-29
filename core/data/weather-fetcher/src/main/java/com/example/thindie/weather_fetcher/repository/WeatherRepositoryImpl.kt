package com.example.thindie.weather_fetcher.repository

import com.example.thindie.core.network.WeatherApiService
import com.example.thindie.core.network.di.DispatchersIOModule
import com.example.thindie.core.network.dto.WeatherDailyDto
import com.example.thindie.core.network.dto.WeatherHourlyDto
import com.example.thindie.core.network.dto.dailydto.map
import com.example.thindie.core.network.dto.hourlydto.map
import com.example.thindie.database.room.WeatherDailyDao
import com.example.thindie.database.room.WeatherDailyDbModel
import com.example.thindie.database.room.WeatherHourlyDao
import com.example.thindie.database.room.WeatherHourlyDbModel
import com.example.thindie.domain.RatificationAble
import com.example.thindie.domain.WeatherRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.weather_fetcher.mappers.map
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

@Singleton
internal class WeatherRepositoryImpl @Inject constructor(
    private val dailyDao: WeatherDailyDao,
    private val hourlyDao: WeatherHourlyDao,
    private val apiService: WeatherApiService,
    @Named("fetchController") private val fetchController: RatificationAble,
    @Named("networkController") private val netWorkController: RatificationAble,
    @DispatchersIOModule.IODispatcher private val ioDispatcher: CoroutineDispatcher,
) : WeatherRepository {
    override suspend fun getDailyWeather(forecastAble: ForecastAble): Result<WeatherDaily> {
        return if (netWorkController.isAllowed()) {
            Result.success(getDaily(forecastAble))
        } else Result.failure(IllegalStateException())
    }

    override suspend fun getHourlyWeather(forecastAble: ForecastAble): Result<WeatherHourly> {
        return if (netWorkController.isAllowed()) {
            Result.success(getHourly(forecastAble))
        } else Result.failure(IllegalStateException())
    }

    override suspend fun getWeatherDailyList(): Result<List<WeatherDaily>> {
        val dbList = getDailyListDbModels()
        val forecastAbleList = getForecastAble(dbList)
        return try {
            val modelList = fetchAndGetDailyModelsList(forecastAbleList)
            modelList.forEach { daily ->
                dailyDao.upsertWeatherSite(daily.map())
            }
            Result.success(modelList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun requestWeatherHourly() {
        if (!fetchController.isAllowed()) return
        delay(1000)
        val dbModelList = dailyDao.getAllWeathersSite()
        val forecastAbleList = getForecastAble(dbModelList)
        if (forecastAbleList.isNotEmpty()) {
            val updatedDbModelList = fetchAndGetHourlyDbModelsList(forecastAbleList)
            updatedDbModelList.forEach { weatherHourlyDbModel ->
                hourlyDao.upsertWeatherSite(weatherHourlyDbModel)
            }
        }
    }

    override fun observeWeatherHourlyList(): Flow<List<WeatherHourly>> {
        return hourlyDao
            .observeAllWeathersSite()
            .simplyMap()
            .flowOn(ioDispatcher)
    }


    override suspend fun deleteWeather(place: String) {
        val daily = getDailyDbModelByPlace(place)
        val hourly = getHourlyDbModelByPlace(place)
        deleteDaily(daily)
        deleteHourly(hourly)
    }

    override suspend fun rememberWeatherLocation(forecastAble: ForecastAble) {
        if (netWorkController.isAllowed())
            try {
                val dailyDbModel = getDailyDto(forecastAble).map(forecastAble).map()
                val hourlyDbModel = getHourlyDto(forecastAble).map(forecastAble).map()
                hourlyDao.upsertWeatherSite(hourlyDbModel)
                dailyDao.upsertWeatherSite(dailyDbModel)
            } catch (_: Exception) {
            }
    }


    private suspend fun fetchAndGetDailyModelsList(list: List<ForecastAble>): List<WeatherDaily> {
        return if (netWorkController.isAllowed())
            list.mapIndexed { _, forecastAble ->
                getDailyDto(forecastAble).map(forecastAble)
            }
        else return emptyList()
    }

    private fun Flow<List<WeatherHourlyDbModel>>.simplyMap(): Flow<List<WeatherHourly>> {
        return map { list ->
            list.map {
                it.map()
            }
        }
    }

    private suspend fun fetchAndGetHourlyDbModelsList(list: List<ForecastAble>): List<WeatherHourlyDbModel> {
        return if (netWorkController.isAllowed()) list.map { getHourlyDto(it).map(forecastAble = it) }
            .map {
                it.map()
            }
        else return emptyList()
    }

    private suspend fun getHourlyDto(forecastAble: ForecastAble): WeatherHourlyDto {
        return apiService.getHourlyWeather(
            latitude = forecastAble.getSightLatitude(),
            longitude = forecastAble.getSightLongitude(),
            timeZone = forecastAble.getTimeZone(),
        ).map()
    }

    private suspend fun getDailyDto(forecastAble: ForecastAble): WeatherDailyDto {
        return apiService.getDailyWeather(
            latitude = forecastAble.getSightLatitude(),
            longitude = forecastAble.getSightLongitude(),
            timeZone = forecastAble.getTimeZone(),
        ).map()
    }


    private suspend fun getDailyListDbModels(): List<WeatherDailyDbModel> {
        return dailyDao.getAllWeathersSite()
    }

    private fun getForecastAble(list: List<WeatherDailyDbModel>): List<ForecastAble> {
        return list.map { dbModel ->
            object : ForecastAble {
                override fun getSight(): String {
                    return dbModel.place
                }

                override fun getSightLatitude(): Float {
                    return dbModel.latitude.toFloat()
                }

                override fun getSightLongitude(): Float {
                    return dbModel.longitude.toFloat()
                }

                override fun getTimeZone(): String {
                    return dbModel.timezone
                }

            }
        }
    }

    private suspend fun getHourlyDbModelByPlace(place: String): WeatherHourlyDbModel? {
        return try {
            hourlyDao.getWeatherSite(place)
        } catch (_: Exception) {
            null
        }
    }

    private suspend fun getDailyDbModelByPlace(place: String): WeatherDailyDbModel? {
        return try {
            dailyDao.getWeatherSite(place)
        } catch (_: Exception) {
            null
        }
    }

    private suspend fun deleteHourly(dbModel: WeatherHourlyDbModel?) {
        try {
            if (dbModel != null) {
                hourlyDao.deleteWeatherSite(dbModel)
            }
        } catch (_: Exception) {
        }
    }

    private suspend fun deleteDaily(dbModel: WeatherDailyDbModel?) {
        if (dbModel != null) {
            dailyDao.deleteWeatherSite(dbModel)
        }
    }

    private suspend fun getDaily(forecastAble: ForecastAble): WeatherDaily {
        val dailyDto = getDailyDto(forecastAble)
        return dailyDto.map(forecastAble)
    }

    private suspend fun getHourly(forecastAble: ForecastAble): WeatherHourly {
        val hourlyDto = getHourlyDto(forecastAble)
        return hourlyDto.map(forecastAble)
    }
}