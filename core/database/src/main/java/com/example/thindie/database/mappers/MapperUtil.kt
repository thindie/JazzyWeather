package com.example.thindie.core.network.mapper

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
    require(weatherMap.containsKey(this)) { " unexpected weather code in network::mapper::MapperUtil " }
    return checkNotNull(weatherMap[this])

}