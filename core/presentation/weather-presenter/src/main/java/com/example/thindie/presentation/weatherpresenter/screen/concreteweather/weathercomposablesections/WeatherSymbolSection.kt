package com.example.thindie.presentation.weatherpresenter.screen.concreteweather.weathercomposablesections

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun WeatherSymbol(modifier: Modifier = Modifier, @DrawableRes weatherRes: Int) {
    Surface(
        modifier = modifier
            .clip(CircleShape)
    ) {
        Icon(
            painter = painterResource(id = weatherRes),
            contentDescription = "",
            modifier = modifier
                .padding(all = 15.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}
