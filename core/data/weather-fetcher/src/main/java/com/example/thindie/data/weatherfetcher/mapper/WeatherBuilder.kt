package com.example.thindie.data.weatherfetcher.mapper

import com.example.thindie.core.network.dto.WeatherDto
import com.example.thindie.core.network.dto.WeatherHourlyDto
import com.example.thindie.database.room.PinnedWeatherDbModel
import com.example.thindie.domain.weatherprovider.entity.Weather

internal class WeatherBuilder private constructor(
    private val weatherDto: WeatherDto,
    private val weatherHourlyDto: WeatherHourlyDto,
    private val place: String,
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

    companion object {
        fun build(
            place: String,
            weather: WeatherDto,
            hourlyWeather: WeatherHourlyDto,
        ): Weather {
            return WeatherBuilder(weather, hourlyWeather, place)
                .build()
        }
    }
}

fun Weather.toPinnedWeather(): PinnedWeatherDbModel {
    return PinnedWeatherDbModel(
        place = place,
        latitude = latitude,
        longitude = longitude,
        temperature = temperature,
        time = time,
        weathercode = weathercode,
        winddirection = winddirection,
        windspeed = windspeed,
        apparentTemperatureMax = apparentTemperatureMax,
        apparentTemperatureMin = apparentTemperatureMin,
        precipitationSum = precipitationSum,
        rainSum = rainSum,
        showersSum = showersSum,
        snowfallSum = snowfallSum,
        sunrise = sunrise,
        sunset = sunset,
        temperatureMaxHourly = temperatureMaxHourly,
        temperatureMinHourly = temperatureMinHourly,
        timesHourly = timesHourly,
        weatherCodesHourly = weatherCodesHourly,
        windgusts10mMaxHourly = windgusts10mMaxHourly,
        windspeed10mMaxHourly = windspeed10mMaxHourly,
        isPlus = isPlus
    )
}

fun PinnedWeatherDbModel.toWeather(): Weather {
    return Weather(
        place = place,
        latitude = latitude,
        longitude = longitude,
        temperature = temperature,
        time = time,
        weathercode = weathercode,
        winddirection = winddirection,
        windspeed = windspeed,
        apparentTemperatureMax = apparentTemperatureMax,
        apparentTemperatureMin = apparentTemperatureMin,
        precipitationSum = precipitationSum,
        rainSum = rainSum,
        showersSum = showersSum,
        snowfallSum = snowfallSum,
        sunrise = sunrise,
        sunset = sunset,
        temperatureMaxHourly = temperatureMaxHourly,
        temperatureMinHourly = temperatureMinHourly,
        timesHourly = timesHourly,
        weatherCodesHourly = weatherCodesHourly,
        windgusts10mMaxHourly = windgusts10mMaxHourly,
        windspeed10mMaxHourly = windspeed10mMaxHourly
    )
}