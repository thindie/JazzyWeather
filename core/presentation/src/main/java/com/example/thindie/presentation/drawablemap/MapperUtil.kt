package com.example.thindie.presentation.drawablemap

import com.example.thindie.presentation.R
import com.example.thindie.presentation.ShortWeatherPresenter

internal val weatherMap = mapOf(
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

internal val weatherDrawableMap = mapOf(
    0 to R.drawable.weather_sunny,
    1 to R.drawable.weather_partly_cloudy,
    2 to R.drawable.weather_partly_cloudy,
    3 to R.drawable.weather_partly_rainy,
    45 to R.drawable.weather_fog,
    48 to R.drawable.weather_fog,
    51 to R.drawable.weather_hail,
    53 to R.drawable.weather_hail,
    55 to R.drawable.weather_hail,
    56 to R.drawable.weather_partly_snowy,
    57 to R.drawable.weather_partly_snowy,
    61 to R.drawable.weather_partly_rainy,
    63 to R.drawable.weather_rainy,
    65 to R.drawable.weather_rainy,
    66 to R.drawable.snowflake_melt,
    67 to R.drawable.snowflake_melt,
    71 to R.drawable.weather_partly_snowy,
    73 to R.drawable.weather_snowy,
    75 to R.drawable.weather_snowy_heavy,
    77 to R.drawable.snowflake_alert,
    80 to R.drawable.weather_pouring,
    81 to R.drawable.weather_pouring,
    82 to R.drawable.weather_pouring,
    85 to R.drawable.snowflake_alert,
    86 to R.drawable.snowflake_alert,
    95 to R.drawable.weather_partly_lightning,
    96 to R.drawable.weather_lightning,
    99 to R.drawable.flash_alert_outline,
)

internal fun Int.present(): ShortWeatherPresenter {
    val weatherCode = this

    return object : ShortWeatherPresenter {
        override val weatherDrawable: Int
            get() {
                require(weatherDrawableMap.containsKey(weatherCode)) {
                    " unexpected weather code in com.example.thindie.presentation.drawablemap " }
                return checkNotNull(weatherDrawableMap[weatherCode])
            }
        override val weatherCode: Int
            get() = weatherCode
        override val nameType: String
            get() {
                require(weatherMap.containsKey(weatherCode)) {
                    " unexpected weather code in com.example.thindie.presentation.drawablemap " }
                return checkNotNull(weatherMap[weatherCode])
            }
    }
}


