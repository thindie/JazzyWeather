package com.example.thindie.domain.usecases

import com.example.thindie.domain.WeatherRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.domain.entities.WeatherHourly
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.flow.Flow

class ReserveWeatherInteractor @Inject constructor(@Named("reserveBus") private val repository: WeatherRepository) {

    suspend fun updateWeatherSite(forecastAble: ForecastAble) {
        return repository.rememberWeatherLocation(forecastAble)
    }

    suspend fun getDailyWeatherSite(forecastAble: ForecastAble): WeatherDaily {
        return repository.getDailyWeather(forecastAble).getOrThrow()
    }

    fun getWeatherHourlyReserveList(): Flow<List<WeatherHourly>> {
        return repository.observeWeatherHourlyList()
    }
}
