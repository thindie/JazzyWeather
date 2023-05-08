package com.example.thindie.core.network.weatherproviderimpl

import android.util.Log
import com.example.thindie.core.network.WeatherApiService
import com.example.thindie.core.network.WeatherProvider
import com.example.thindie.core.network.WeatherProviderContract
import com.example.thindie.core.network.dto.WeatherDto
import com.example.thindie.core.network.dto.WeatherDtoBuilder
import com.example.thindie.core.network.dto.WeatherHourlyDto
import com.example.thindie.core.network.dto.WeatherHourlyDtoBuilder
import com.example.thindie.core.network.util.serviceResponseThrowOrResult
import javax.inject.Inject

internal class WeatherProviderImpl @Inject constructor(private val service: WeatherApiService) :
    WeatherProvider {
    @kotlin.jvm.Throws(IllegalStateException::class)
    override suspend fun provideDailyWeather(contract: WeatherProviderContract): WeatherDto {
        return WeatherDtoBuilder {
                serviceResponseThrowOrResult {
                    service.getWeatherJson(
                        latitude = contract.latitude,
                        longitude = contract.longitude,
                        timeZone = contract.timeZone
                    ).also {
                        Log.d("SERVICE_TAG", it.body().toString())
                    }
                }
            }

    }

    @kotlin.jvm.Throws(IllegalStateException::class)
    override suspend fun provideHourlyWeather(contract: WeatherProviderContract): WeatherHourlyDto {
        val weather =
            WeatherHourlyDtoBuilder {
                serviceResponseThrowOrResult {
                    service.getHourlyWeatherJson(
                        latitude = contract.latitude,
                        longitude = contract.longitude,
                        timeZone = contract.timeZone
                    )
                }
            }
        return checkNotNull(weather)
    }
}