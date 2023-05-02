package com.example.thindie.presentation

import androidx.annotation.DrawableRes

interface ShortWeatherPresenter {
    @get:DrawableRes
    val weatherDrawable: Int
    val weatherCode: Int
    val nameType: String
}

class ShortWeatherP