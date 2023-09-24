package com.example.thindie.location_presenter.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.location_presenter.screen.LocationPickerScreen

internal const val picker = "picker"

fun NavGraphBuilder.LocationPicker(
    onClickConcrete: (ForecastAble) -> Unit,
) {
    composable(route = picker) {
        LocationPickerScreen(
            onClickConcrete = onClickConcrete,
        )
    }

}