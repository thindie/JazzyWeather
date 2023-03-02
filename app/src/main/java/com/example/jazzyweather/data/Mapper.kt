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
        weathercode = remoteSourceDto.weathercode,
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
        timeZone = "",
        adaptedTimeZone = ""
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
    0 to "Ясно",
    1 to "В основном Ясно",
    2 to "Небольшая Облачность",
    3 to "Пасмурно",
    45 to "Туман",
    48 to "Туман и изморозь",
    51 to "Морось",
    53 to "Противная Морось",
    55 to "Противнейшая Морось",
    56 to "Легкая Изморозь",
    57 to "Интенсивная Изморозь",
    61 to "Дождь: незначительно",
    63 to "Дождь: средне",
    65 to "Сильный Дождь",
    66 to "Легкий Ледяной Дождь",
    67 to "Сильный Ледяной Дождь",
    71 to "Незначительный снег",
    73 to "Снег",
    75 to "Сильный Снег",
    77 to "Град",
    80 to "Небольшой Ливень",
    81 to "Ливень",
    82 to "Сильный Ливень",
    85 to "Снегопад",
    86 to "Сильный Снегопад",
    95 to "Небольшая Гроза",
    96 to "Гроза",
    99 to "Грозище",
)

fun Int.whatWeatherForHuman(): String {
    return weatherMap[this] ?: "unstable weather type"
}
