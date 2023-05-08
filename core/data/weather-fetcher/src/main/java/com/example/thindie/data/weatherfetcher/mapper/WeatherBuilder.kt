package com.example.thindie.data.weatherfetcher.mapper

import com.example.thindie.core.network.dto.WeatherDto
import com.example.thindie.core.network.dto.WeatherHourlyDto
import com.example.thindie.domain.weatherprovider.entity.Weather

internal class WeatherBuilder private constructor(
    private val weatherDto: WeatherDto,
    private val weatherHourlyDto: WeatherHourlyDto,
    private val place: String
) {
    private fun build() =
    Weather(
        place = place,
        latitude = weatherDto.latitude.toFloat(),
        longitude = weatherDto.longitude.toFloat(),
        temperature = weatherDto.temperature,
        time = weatherDto.time,
        weathercode = weatherDto.weatherCode,
        winddirection = weatherDto.windDirection,
        windspeed = weatherDto.windSpeed,
        apparentTemperatureMax = weatherDto.apparentTemperatureMax,
        apparentTemperatureMin = weatherDto.apparentTemperatureMin,
        precipitationSum = weatherDto.precipitationSum,
        rainSum = weatherDto.rainSum,
        showersSum = weatherDto.showersSum,
        snowfallSum = weatherDto.snowfallSum,
        sunrise = weatherDto.sunrise,
        sunset = weatherDto.sunset,
        temperatureMaxHourly = weatherHourlyDto.temperatureTwoMeters,
        temperatureMinHourly = weatherHourlyDto.temperatureTwoMeters,
        timesHourly = weatherHourlyDto.time,
        weatherCodesHourly = weatherHourlyDto.weatherCode,
        windgusts10mMaxHourly = weatherHourlyDto.windspeedTenMeters,
        windspeed10mMaxHourly = weatherHourlyDto.windspeedTenMeters,
    ) //todo(
    companion object{
        suspend fun build(
            place: String,
            weatherDtoFetcher: suspend () -> WeatherDto,
            hourlyWeatherFetcher: suspend () -> WeatherHourlyDto
        ): Weather{
           return WeatherBuilder(weatherDtoFetcher(), hourlyWeatherFetcher(), place)
                .build()
        }
    }
}