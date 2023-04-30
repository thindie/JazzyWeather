package com.example.thindie.data.weatherfetcher.repositoryimpl

import com.example.thindie.common.DispatchersIOModule
import com.example.thindie.core.network.WeatherProvider
import com.example.thindie.core.network.WeatherProviderContract
import com.example.thindie.core.network.util.FAKE_TIME_ZONE
import com.example.thindie.data.weatherfetcher.mapper.WeatherBuilder
import com.example.thindie.database.room.WeatherDbModel
import com.example.thindie.database.room.WeatherRoomDao
import com.example.thindie.domain.weatherprovider.entity.Weather
import com.example.thindie.domain.weatherprovider.repository.WeatherFetcherRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
@Singleton
internal class WeatherFetcherRepositoryImpl @Inject constructor(
    private val service: WeatherProvider,
    private val dao: WeatherRoomDao,
    @DispatchersIOModule.IODispatcher private val dispatcherIO: CoroutineDispatcher
) : WeatherFetcherRepository {
    override suspend fun fetchWeather(city: String): Weather {

        val weatherLandedProvider: WeatherLandedProvider = withContext(dispatcherIO) {
            dao.getWeatherSite(city)
        }.build()

        return withContext(dispatcherIO) {
            WeatherBuilder.build(
                place = city,
                weatherDtoFetcher = { service.provideDailyWeather(weatherLandedProvider) },
                hourlyWeatherFetcher = { service.provideHourlyWeather(weatherLandedProvider) },
            )
        }
    }

    private fun WeatherDbModel.build(): WeatherLandedProvider {
        return WeatherLandedProvider(
            latitude = this.latitude, longitude = this.longitude, timeZone = FAKE_TIME_ZONE
        )
    }

    data class WeatherLandedProvider(
        override val latitude: Float, override val longitude: Float, override val timeZone: String
    ) : WeatherProviderContract

}