package com.example.jazzyweather.navigation

import androidx.annotation.DrawableRes
import com.example.thindie.designsystem.NavigationAble
import com.example.thindie.presentation.R


data class NavRowElement(
    val simpleRoute: String,
    @DrawableRes val image: Int,
) : NavigationAble {
    override fun getRoute() = simpleRoute

    override fun getVision() = image

}

val back = NavRowElement(goBack, R.drawable.nav_icon_arrow)
val locations = NavRowElement(picker, R.drawable.nav_icon_search)
val favorites = NavRowElement(all, R.drawable.nav_icon_favorite)

val navigationAbles = listOf(back, locations, favorites)