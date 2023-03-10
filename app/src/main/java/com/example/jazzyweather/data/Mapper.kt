package com.example.jazzyweather.data

import com.example.jazzyweather.data.local.PossibilityFromJson
import com.example.jazzyweather.data.local.WeatherDBModel
import com.example.jazzyweather.data.local.possibilities.PossibilititesDbModel
import com.example.jazzyweather.data.remote.WeatherDTO
import com.example.jazzyweather.data.remote.WeatherHourlyDTO
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.domain.Weather
import com.example.jazzyweather.domain.WeatherHourly
import com.example.jazzyweather.domain.WeatherOffline

private const val FAKE_TIME_ZONE = "Europe/Moscow"
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
fun Weather.toPossibility():Possibility{
    return Possibility(this.place, this.latitude, this.longitude, FAKE_TIME_ZONE, "")
}

fun WeatherHourlyDTO.fromDTOtoModel(): WeatherHourly {
    return WeatherHourly(
        this.place,
        this.latitude,
        this.longitude,
        this.precipitation,
        this.temperature_2m,
        this.time,
        this.weathercode,
        this.windspeed_10m
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

fun Possibility.toDBModel(): PossibilititesDbModel {

    return PossibilititesDbModel(
        this.place,
        this.latitude,
        this.longitude,
        this.timeZone,
        this.admin_name
    )
}

fun PossibilititesDbModel.toModel(): Possibility {

    return Possibility(
        this.place,
        this.latitude,
        this.longitude,
        this.timeZone,
        this.admin_name
    )
}

fun WeatherDBModel.fromDBtoPossibility(): Possibility {
    return Possibility(
        place = this.place,
        latitude = this.latitude,
        longitude = this.longitude,
        timeZone = FAKE_TIME_ZONE,
         admin_name = ""
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
    0 to "????????",
    1 to "?? ???????????????? ????????",
    2 to "?????????????????? ????????????????????",
    3 to "????????????????",
    45 to "??????????",
    48 to "?????????? ?? ????????????????",
    51 to "????????????",
    53 to "?????????????????? ????????????",
    55 to "???????????????????????? ????????????",
    56 to "???????????? ????????????????",
    57 to "????????????????!",
    61 to "????????????",
    63 to "??????????",
    65 to "?????????????? ??????????",
    66 to "?????????????? ??????????",
    67 to "*???*?! ?????????????? ??????????!!",
    71 to "?????????????????? ????????",
    73 to "????????",
    75 to "?????????????? ????????",
    77 to "????????",
    80 to "?????????????????? ????????????",
    81 to "????????????",
    82 to "????????",
    85 to "????????????????",
    86 to "?????????????? ????????????????",
    95 to "?????????????????? ??????????",
    96 to "??????????",
    99 to "??????????????",
)

fun Int.whatWeatherForHuman(): String {
    return weatherMap[this] ?: "unstable weather type"
}

fun PossibilityFromJson.map(): Possibility {
    return Possibility(
        place = city.transmutate(),
        latitude = lat.toFloat(),
        longitude = lng.toFloat(),
        timeZone = FAKE_TIME_ZONE,
        admin_name = admin_name.transmutate()
    )
}
