package com.example.thindie.weather_fetcher.repository

import com.example.thindie.core.network.WeatherApiService
import com.example.thindie.core.network.di.DispatchersIOModule
import com.example.thindie.database.room.WeatherDailyDao
import com.example.thindie.database.room.WeatherHourlyDao
import com.example.thindie.domain.RatificationObserver
import com.example.thindie.domain.ForecastAbleRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.domain.entities.WeatherHourly
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Singleton
internal class WeatherHourlyRepositoryImpl @Inject constructor(
    private val dailyDao: WeatherDailyDao,
    private val hourlyDao: WeatherHourlyDao,
    private val apiService: WeatherApiService,
    @Named("fetchController") private val fetchController: RatificationObserver,
    @Named("networkController") private val netWorkController: RatificationObserver,
    @DispatchersIOModule.IODispatcher private val ioDispatcher: CoroutineDispatcher,
) : ForecastAbleRepository<WeatherHourly> {

    private val state = MutableStateFlow(
        RepositoryState(
            forecastAble = null,
            isFetchAllowed = false,
            isNetworkAvailAble = false,
            foreCastAbleList = emptyList()
        )
    )

    override suspend fun requestForecastAble(forecastAble: ForecastAble) {
        TODO("Not yet implemented")
    }

    override suspend fun requestConcreteDateForecastAble(
        simpleIso8106: String,
        forecastAble: ForecastAble,
    ) {
        TODO("Not yet implemented")
    }

    override fun observeForecastAbleList(): Flow<List<WeatherHourly>> {
        TODO("Not yet implemented")
    }

    override fun observeForecastAble(): Flow<WeatherHourly> {
        TODO("Not yet implemented")
    }


    override suspend fun deleteForecastAble(place: String) {
        TODO("Not yet implemented")
    }

    override suspend fun rememberWeatherLocation(forecastAble: ForecastAble) {
        TODO("Not yet implemented")
    }


}