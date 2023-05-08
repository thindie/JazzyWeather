package com.example.thindie.data.weatherfetcher.repositoryimpl

import com.example.thindie.common.DispatchersIOModule
import com.example.thindie.core.network.WeatherProvider
import com.example.thindie.core.network.WeatherProviderContract
import com.example.thindie.core.network.util.FAKE_TIME_ZONE
import com.example.thindie.data.weatherfetcher.mapper.WeatherBuilder
import com.example.thindie.database.room.WeatherDbModel
import com.example.thindie.database.room.WeatherRoomDao
import com.example.thindie.domain.weatherprovider.contract.WeatherFetchRequirements
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
    override suspend fun fetchWeather(requirements: WeatherFetchRequirements): Weather {
        return try {
            val dbModel = withContext(dispatcherIO) {
                requireNotNull(dao.getWeatherSite(requirements.location))
            }
            val weatherLandedProvider: WeatherLandedProvider = dbModel.build()
            requirements.location
                .prepareWeatherForecast(weatherLandedProvider)
                .alsoUpsertDao(dbModel.isPinned)

        } catch (e: IllegalArgumentException) {
            val weatherLandedProvider: WeatherLandedProvider = requirements.build()
            requirements.location
                .prepareWeatherForecast(weatherLandedProvider)
                .alsoUpsertDao(false)
        }
    }

    override suspend fun fetchPinnedWeatherLocations(): List<Weather> {
        return dao.getAllWeathersSite()
            .filter { weatherDbModel -> weatherDbModel.isPinned }
            .map { weatherDbModel ->
                val landedProvider = weatherDbModel.build()
                val weather = weatherDbModel.place.prepareWeatherForecast(landedProvider)
                weather.alsoUpsertDao(weatherDbModel.isPinned)
            }
    }

    override suspend fun pinWeather(city: String) {
        val dbModelToUpsert = dao.getWeatherSite(city)

        dao.upsertWeatherSite(dbModelToUpsert.copy(isPinned = !dbModelToUpsert.isPinned))
    }

    private suspend fun String.prepareWeatherForecast(weatherLandedProvider: WeatherLandedProvider): Weather {
        return withContext(dispatcherIO) {
            WeatherBuilder.build(
                place = this@prepareWeatherForecast,
                weatherDtoFetcher = {
                    requireNotNull(
                        service.provideDailyWeather(
                            weatherLandedProvider
                        )
                    )
                },
                hourlyWeatherFetcher = {
                    requireNotNull(
                        service.provideHourlyWeather(
                            weatherLandedProvider
                        )
                    )
                },
            )
        }
    }


    private fun WeatherDbModel.build(): WeatherLandedProvider {
        return WeatherLandedProvider(
            latitude = this.latitude, longitude = this.longitude, timeZone = FAKE_TIME_ZONE
        )
    }

    private fun WeatherFetchRequirements.build(): WeatherLandedProvider {
        return WeatherLandedProvider(
            latitude = this.latitude, longitude = this.longitude, timeZone = FAKE_TIME_ZONE
        )
    }

    private suspend fun Weather.alsoUpsertDao(isPinned: Boolean): Weather {
        val weatherDbModel = WeatherDbModel(
            place = place,
            latitude = latitude,
            longitude = longitude,
            temperature = temperature,
            time = time,
            weatherCode = weathercode,
            windDirection = winddirection,
            windSpeed = windspeed,
            isPinned = isPinned
        )
        dao.upsertWeatherSite(weatherDbModel)
        return this@alsoUpsertDao
    }

    data class WeatherLandedProvider(
        override val latitude: Float, override val longitude: Float, override val timeZone: String
    ) : WeatherProviderContract

}