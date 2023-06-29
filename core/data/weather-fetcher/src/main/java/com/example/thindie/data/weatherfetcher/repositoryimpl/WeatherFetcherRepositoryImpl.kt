package com.example.thindie.data.weatherfetcher.repositoryimpl

import com.example.thindie.core.network.WeatherProvider
import com.example.thindie.core.network.WeatherProviderContract
import com.example.thindie.core.network.util.FAKE_TIME_ZONE
import com.example.thindie.data.weatherfetcher.mapper.WeatherBuilder
import com.example.thindie.data.weatherfetcher.mapper.toPinnedWeather
import com.example.thindie.data.weatherfetcher.mapper.toWeather
import com.example.thindie.database.room.PinnedWeatherDao
import com.example.thindie.database.room.PinnedWeatherDbModel
import com.example.thindie.domain.weatherprovider.contract.WeatherFetchRequirements
import com.example.thindie.domain.weatherprovider.entity.Weather
import com.example.thindie.domain.weatherprovider.repository.WeatherFetcherRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single

@Singleton
internal class WeatherFetcherRepositoryImpl @Inject constructor(
    private val service: WeatherProvider,
    private val savedDao: PinnedWeatherDao,
) : WeatherFetcherRepository {
    override suspend fun fetchWeather(requirements: WeatherFetchRequirements): Result<Weather> {
        return runCatching {
            fetchFromService(requirements)
        }
    }

    override fun fetchPinnedWeatherLocations(): Flow<Result<List<Weather>>> {
        return savedDao
            .getAllWeather()
            .map { list ->
                list.runCatching {
                    map { it.toWeather() }
                }
            }
    }


    override suspend fun pinWeather(requirements: WeatherFetchRequirements) {
        kotlin.runCatching {
            fetchWeather(requirements).getOrThrow()
        }
            .onSuccess {
                savedDao.upsertWeather(it.toPinnedWeather())
            }
            .onFailure {
                error(" web malfunction ")
            }
    }

    override suspend fun updateSavedWeatherPlaces() {
        kotlin.runCatching {
            savedDao.getAllWeather().single()
        }
            .onSuccess { list ->
                assistUpdate(list)
            }
    }

    private suspend fun assistUpdate(list: List<PinnedWeatherDbModel>) {
        kotlin.runCatching {
            list.map { savedWeather ->
                fetchFromService(
                    object : WeatherFetchRequirements {
                        override val location: String
                            get() = savedWeather.place
                        override val latitude: Float
                            get() = savedWeather.latitude
                        override val longitude: Float
                            get() = savedWeather.longitude
                    }
                )
            }
        }.onSuccess { weathersList ->
            weathersList.forEach { weather ->
                savedDao.upsertWeather(weather.toPinnedWeather())
            }
        }
    }

    override suspend fun deleteWeather(place: String) {
        savedDao.deleteWeather(place)
    }

    private suspend fun fetchFromService(requirements: WeatherFetchRequirements): Weather {
        val dailyWeather = service.provideDailyWeather(requirements.build())
        val hourlyWeather = service.provideHourlyWeather(requirements.build())
        return WeatherBuilder.build(
            requirements.location, weather = dailyWeather, hourlyWeather = hourlyWeather
        )
    }


    private fun WeatherFetchRequirements.build(): WeatherLandedProvider {
        return WeatherLandedProvider(
            latitude = this.latitude, longitude = this.longitude, timeZone = FAKE_TIME_ZONE
        )
    }


    data class WeatherLandedProvider(
        override val latitude: Float,
        override val longitude: Float,
        override val timeZone: String,
    ) : WeatherProviderContract

}