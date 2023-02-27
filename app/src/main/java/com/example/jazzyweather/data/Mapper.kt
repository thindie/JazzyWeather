package com.example.jazzyweather.data

import com.example.jazzyweather.data.local.WeatherDBModel
import com.example.jazzyweather.data.remote.WeatherDTO
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.domain.Weather
import com.example.jazzyweather.domain.WeatherOffline

fun Possibility.combineWith(remoteSourceDto: WeatherDTO, place: String): Weather {
    return Weather(
        place = place,
        latitude = this.latitude,
        longitude = this.longitude,
        temperature = remoteSourceDto.temperature,
        time = remoteSourceDto.time,
        weathercode = whatWeatherForHuman(remoteSourceDto.weathercode),
        winddirection = remoteSourceDto.winddirection,
        windspeed = remoteSourceDto.windspeed,
        apparent_temperature_max = remoteSourceDto.apparent_temperature_max,
        apparent_temperature_min = remoteSourceDto.apparent_temperature_min,
        precipitation_sum = remoteSourceDto.precipitation_sum,
        rain_sum = remoteSourceDto.rain_sum,
        showers_sum = remoteSourceDto.showers_sum,
        snowfall_sum = remoteSourceDto.snowfall_sum,
        sunrise = remoteSourceDto.sunrise,
        sunset = remoteSourceDto.sunset,
        temperature_2m_max = remoteSourceDto.temperature_2m_max,
        temperature_2m_min = remoteSourceDto.temperature_2m_min,
        times = remoteSourceDto.times,
        weathercodes = remoteSourceDto.weathercodes,
        windgusts_10m_max = remoteSourceDto.windgusts_10m_max,
        windspeed_10m_max = remoteSourceDto.windspeed_10m_max,

        )
}

fun WeatherDBModel.fromDBtoOffline(): WeatherOffline {
    return WeatherOffline(
        place = this.place,
        latitude = this.latitude,
        longitude = this.longitude,
        temperature = this.temperature,
        time = this.time,
        weathercode = this.weathercode,
        winddirection = this.winddirection,
        windspeed = this.windspeed,
    )
}


fun WeatherDBModel.fromDBtoPossibility(): Possibility {
    return Possibility(
        place = this.place,
        latitude = this.latitude,
        longitude = this.longitude,

        )
}

fun Weather.fromDomainToDB(): WeatherDBModel {
    return WeatherDBModel(
        place = this.place,
        latitude = this.latitude,
        longitude = this.longitude,
        temperature = this.temperature,
        time = this.time,
        weathercode = this.weathercode,
        winddirection = this.winddirection,
        windspeed = this.windspeed,
    )
}


val weatherMap = mapOf(
    0 to "Clear sky",
    1 to "Mainly clear",
    2 to "partly cloudy",
    3 to "overcast",
    45 to "Fog",
    48 to "depositing rime fog",
    51 to "Drizzle: Light",
    53 to "Drizzle: moderate",
    55 to "Drizzle: dense intensity",
    56 to "Freezing Drizzle: Light",
    57 to "Freezing Drizzle: dense intensity",
    61 to "Rain: Slight",
    63 to "Rain: moderate",
    65 to "Rain: heavy intensity",
    66 to "Freezing Rain: Light",
    67 to "Freezing Rain: heavy intensity",
    71 to "Snow fall: Slight",
    73 to "Snow fall: moderate",
    75 to "Snow fall: heavy intensity",
    77 to "Snow grains",
    80 to "Rain showers: Slight",
    81 to "Rain showers: moderate",
    82 to "Rain showers: violent",
    85 to "Snow showers slight",
    86 to "Snow showers heavy",
    95 to "Thunderstorm: Slight",
    96 to "Thunderstorm: Slight",
    99 to "Thunderstorm: Slight",
)

fun whatWeatherForHuman(int: Int): String {
    return weatherMap[int] ?: "unstable weather type"
}
