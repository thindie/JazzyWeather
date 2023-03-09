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
    57 to "Изморозь!",
    61 to "Дождик",
    63 to "Дождь",
    65 to "Сильный Дождь",
    66 to "Ледяной Дождь",
    67 to "*№*?! Ледяной Дождь!!",
    71 to "Небольшой снег",
    73 to "Снег",
    75 to "Сильный Снег",
    77 to "Град",
    80 to "Небольшой Ливень",
    81 to "Ливень",
    82 to "Льет",
    85 to "Снегопад",
    86 to "Сильный Снегопад",
    95 to "Небольшая Гроза",
    96 to "Гроза",
    99 to "Грозище",
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
