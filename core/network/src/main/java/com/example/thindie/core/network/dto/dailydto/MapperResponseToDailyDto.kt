package com.example.thindie.core.network.dto.dailydto

import com.example.thindie.core.network.dto.WeatherDailyDto

fun WeatherDailyResponse.map(): WeatherDailyDto {
    return WeatherDailyDto(
        latitude = latitude,
        longitude = longitude,
        timezone = timezone,
        elevation = elevation,
        timeZoneAbbreviation = timeZoneAbbreviation,
        utcOffsetSeconds = utcOffsetSeconds,
        precipitationHours = daily.precipitationHours,
        precipitationProbabilityMax = daily.precipitationProbabilityMax,
        precipitationSum = daily.precipitationSum,
        rainSum = daily.rainSum,
        showersSum = daily.showersSum,
        snowfallSum = daily.snowfallSum,
        time = daily.time,
        uvIndexMax = daily.uvIndexMax,
        windDirection10mDominant = daily.windDirection10mDominant,
        windGusts10mMax = daily.windGusts10mMax,
        windSpeed10mMax = daily.windSpeed10mMax,

        )
}