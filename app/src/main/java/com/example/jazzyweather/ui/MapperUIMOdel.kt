package com.example.jazzyweather.ui

import com.example.jazzyweather.domain.Weather
import com.example.jazzyweather.domain.WeatherHourly

fun Weather.map(): WeatherUIModel {
    return WeatherUIModel(
        place = place,
        latitude = this.latitude,
        longitude = this.longitude,
        temperature = this.temperature,
        time = this.time,
        weathercode = this.weathercode,
        winddirection = this.winddirection,
        windspeed = this.windspeed,
        apparent_temperature_max = this.apparent_temperature_max,
        apparent_temperature_min = this.apparent_temperature_min,
        precipitation_sum = this.precipitation_sum,
        rain_sum = this.rain_sum,
        showers_sum = this.showers_sum,
        snowfall_sum = this.snowfall_sum,
        sunrise = this.sunrise,
        sunset = this.sunset,
        temperature_2m_max = this.temperature_2m_max,
        temperature_2m_min = this.temperature_2m_min,
        times = this.times,
        weathercodes = this.weathercodes,
        windgusts_10m_max = this.windgusts_10m_max,
        windspeed_10m_max = this.windspeed_10m_max,
    )
}

fun Weather.mapHourly(weatherHourly: WeatherHourly): WeatherHourlyUIModel {
    return WeatherHourlyUIModel(
        place = place,
        latitude = this.latitude,
        longitude = this.longitude,
        temperature = this.temperature,
        time = this.time,
        weathercode = this.weathercode,
        winddirection = this.winddirection,
        windspeed = this.windspeed,
        apparent_temperature_max = this.apparent_temperature_max,
        apparent_temperature_min = this.apparent_temperature_min,
        precipitation_sum = this.precipitation_sum,
        rain_sum = this.rain_sum,
        showers_sum = this.showers_sum,
        snowfall_sum = this.snowfall_sum,
        sunrise = this.sunrise,
        sunset = this.sunset,
        temperature_2m_max = this.temperature_2m_max,
        temperature_2m_min = this.temperature_2m_min,
        times = this.times,
        weathercodes = this.weathercodes,
        windgusts_10m_max = this.windgusts_10m_max,
        windspeed_10m_max = this.windspeed_10m_max,
        isPlus = this.isPlus,
        time_hourly = weatherHourly.time,
        precipitation = weatherHourly.precipitation,
        temperature_2m = weatherHourly.temperature_2m,
        weathercode_hourly = weatherHourly.weathercode,
        windspeed_10m_hourly = weatherHourly.windspeed_10m
    )
 }

fun WeatherHourlyUIModel.map(): WeatherUIModel{
    return WeatherUIModel(
        place = place,
        latitude = this.latitude,
        longitude = this.longitude,
        temperature = this.temperature,
        time = this.time,
        weathercode = this.weathercode,
        winddirection = this.winddirection,
        windspeed = this.windspeed,
        apparent_temperature_max = this.apparent_temperature_max,
        apparent_temperature_min = this.apparent_temperature_min,
        precipitation_sum = this.precipitation_sum,
        rain_sum = this.rain_sum,
        showers_sum = this.showers_sum,
        snowfall_sum = this.snowfall_sum,
        sunrise = this.sunrise,
        sunset = this.sunset,
        temperature_2m_max = this.temperature_2m_max,
        temperature_2m_min = this.temperature_2m_min,
        times = this.times,
        weathercodes = this.weathercodes,
        windgusts_10m_max = this.windgusts_10m_max,
        windspeed_10m_max = this.windspeed_10m_max,
    )
}

fun WeatherUIModel.map(): Weather  {
    return Weather(
        place = place,
        latitude = this.latitude,
        longitude = this.longitude,
        temperature = this.temperature,
        time = this.time,
        weathercode = this.weathercode,
        winddirection = this.winddirection,
        windspeed = this.windspeed,
        apparent_temperature_max = this.apparent_temperature_max,
        apparent_temperature_min = this.apparent_temperature_min,
        precipitation_sum = this.precipitation_sum,
        rain_sum = this.rain_sum,
        showers_sum = this.showers_sum,
        snowfall_sum = this.snowfall_sum,
        sunrise = this.sunrise,
        sunset = this.sunset,
        temperature_2m_max = this.temperature_2m_max,
        temperature_2m_min = this.temperature_2m_min,
        times = this.times,
        weathercodes = this.weathercodes,
        windgusts_10m_max = this.windgusts_10m_max,
        windspeed_10m_max = this.windspeed_10m_max,
    )
}