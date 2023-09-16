package com.example.thindie.designsystem

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface DecodeAble {
    @StringRes
    fun decodeString(code: Int): Int

    @DrawableRes
    fun decodeDrawable(code: Int): Int
}