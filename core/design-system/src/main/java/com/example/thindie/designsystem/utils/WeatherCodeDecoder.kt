package com.example.thindie.designsystem.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.thindie.designsystem.DecodeAble
import javax.inject.Inject
import weatherCodeToStringRes

internal class WeatherCodeDecoder @Inject constructor() : DecodeAble {
    @StringRes
    override fun decodeString(code: Int): Int {
        return weatherCodeToStringRes.getValue(code)
    }

    @DrawableRes
    override fun decodeDrawable(code: Int): Int {
        return weatherCodeToDrawableRes.getValue(code)
    }


}