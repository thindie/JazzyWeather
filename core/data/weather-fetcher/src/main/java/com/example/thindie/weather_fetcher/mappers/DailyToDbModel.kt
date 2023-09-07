package com.example.thindie.weather_fetcher.mappers

import com.example.thindie.database.room.WeatherDailyDbModel
import com.example.thindie.domain.entities.WeatherDaily

fun WeatherDaily.map(): WeatherDailyDbModel {
    return WeatherDailyDbModel(
        place = place,
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