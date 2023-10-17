package com.example.thindie.location_presenter.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.domain.entities.WeatherLocation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LocationPickerUnit(
    modifier: Modifier = Modifier,
    location: WeatherLocation,
    onClickCard: () -> Unit,
) {
    OutlinedCard(onClick = onClickCard) {
        Text(text = location.city)
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewLocationPickerUnit() {
    JazzyWeatherTheme {

    }
}