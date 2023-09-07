package com.example.thindie.weather_fetcher.mappers

import com.example.thindie.core.network.dto.WeatherDailyDto
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily

fun WeatherDailyDto.map(forecastAble: ForecastAble): WeatherDaily {
    return WeatherDaily(
        place = forecastAble.getSight(),
        latitude = latitude,
        longitude = longitude,
        elevation = elevation,
        timezone = timezone,
        timeZoneAbbreviation = timeZoneAbbreviation,
        utcOffsetSeconds = utcOffsetSeconds,
        precipitationHours = precipitationHours,
        precipitationProbabilityMax = precipitationProbabilityMax,
        precipitationSum = precipitationSum,
        rainSum = rainSum,
        showersSum = showersSum,
        snowfallSum = snowfallSum,
        time = time,
        uvIndexMax = uvIndexMax,
        windDirection10mDominant = windDirection10mDominant,
        windGusts10mMax = windGusts10mMax,
        windSpeed10mMax = windSpeed10mMax
    )
}