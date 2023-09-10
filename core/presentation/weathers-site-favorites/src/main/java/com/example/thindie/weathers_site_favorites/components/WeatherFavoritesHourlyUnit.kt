package com.example.thindie.weathers_site_favorites.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun WeatherFavoriteHourlyUnit(
    modifier: Modifier = Modifier,
    time: String,
    celsium: String,
    windSpeed: String,
    precipitation: String,
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(21.dp))
            .width(228.dp)
            .height(171.dp)
    ) {
        Column(
            modifier = modifier
                .background(Brush.verticalGradient(listOf(Color.Transparent, Color(0xFF5053A9))))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = time,
                letterSpacing = 2.sp,
                style = MaterialTheme.typography.titleSmall.copy(
                )
            )
            Text(
                text = celsium,
                letterSpacing = 2.sp,
                style = MaterialTheme.typography.titleSmall.copy(
                )
            )
            Text(
                text = windSpeed,
                letterSpacing = 2.sp,
                style = MaterialTheme.typography.titleSmall.copy(
                )
            )
            Text(
                text = precipitation,
                letterSpacing = 2.sp,
                style = MaterialTheme.typography.titleSmall.copy(
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherFavoriteHourlyUnit() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {
        WeatherFavoriteHourlyUnit(
            time = "14:00",
            celsium = "5",
            windSpeed = "ветер 4 м/с",
            precipitation = "дождь 24 мм"
        )
    }
}