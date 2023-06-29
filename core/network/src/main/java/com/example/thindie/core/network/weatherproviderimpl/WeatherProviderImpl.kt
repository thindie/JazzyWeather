package com.example.thindie.core.network.weatherproviderimpl

import com.example.thindie.core.network.WeatherApiService
import com.example.thindie.core.network.WeatherProvider
import com.example.thindie.core.network.WeatherProviderContract
import com.example.thindie.core.network.dto.WeatherDto
import com.example.thindie.core.network.dto.WeatherDtoBuilder
import com.example.thindie.core.network.dto.WeatherHourlyDto
import com.example.thindie.core.network.dto.WeatherHourlyDtoBuilder
import javax.inject.Inject

internal class WeatherProviderImpl @Inject constructor(private val service: WeatherApiService) :
    WeatherProvider {

    override suspend fun provideDailyWeather(contract: WeatherProviderContract): WeatherDto {
        return WeatherDtoBuilder() {
            service.getWeatherJson(
                latitude = contract.latitude,
                longitude = contract.longitude,
                timeZone = contract.timeZone
            )
        }.getOrThrow()
    }

    override suspend fun provideHourlyWeather(contract: WeatherProviderContract): WeatherHourlyDto {
        return WeatherHourlyDtoBuilder {
            service.getHourlyWeatherJson(
                latitude = contract.latitude,
                longitude = contract.longitude,
                timeZone = contract.timeZone
            )
        }.getOrThrow()
    }
}