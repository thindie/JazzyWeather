package com.example.thindie.weather_fetcher.mappers

import com.example.thindie.core.network.dto.WeatherHourlyDto
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherHourly

fun WeatherHourlyDto.map(forecastAble: ForecastAble): WeatherHourly {
    return WeatherHourly(
        place = forecastAble.getSight(),
        latitude = latitude,
        longitude = longitude,
        timezone = timezone,
        timeZoneAbbreviation = timeZoneAbbreviation,
        utcOffsetSeconds = utcOffsetSeconds,
        apparentTemperature = apparentTemperature,
        precipitation = precipitation,
        rain = rain,
        showers = showers,
        snowfall = snowfall,
        temperature2m = temperature2m,
        time = time,
        visibility = visibility,
        weatherCode = weatherCode,
        windGusts10m = windGusts10m,
        windSpeed10m = windSpeed10m
    )
}