package com.example.thindie.designsystem

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface VisualizeAble {
    @DrawableRes
    fun getIcon(): Int

    @StringRes
    fun getTitle(): Int
}