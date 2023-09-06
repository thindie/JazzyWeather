package com.example.thindie.core.network.dto.hourlydto

import com.example.thindie.core.network.dto.WeatherHourlyDto

fun WeatherHourlyResponse.map(): WeatherHourlyDto {
    return WeatherHourlyDto(
        latitude = latitude,
        longitude = longitude,
        timezone = timezone,
        timeZoneAbbreviation = timeZoneAbbreviation,
        utcOffsetSeconds = utcOffsetSeconds,
        apparentTemperature = hourly.apparentTemperature,
        precipitation = hourly.precipitation,
        rain = hourly.rain,
        showers = hourly.showers,
        snowfall = hourly.snowfall,
        temperature2m = hourly.temperature2m,
        time = hourly.time,
        visibility = hourly.visibility,
        weatherCode = hourly.weatherCode,
        windGusts10m = hourly.windGusts10m,
        windSpeed10m = hourly.windSpeed10m

    )
}
