package com.example.thindie.location_presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherLocation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LocationPickerUnit(
    modifier: Modifier = Modifier,
    location: WeatherLocation,
    onClickCard: () -> Unit,
    onShowLocation: (ForecastAble) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .height(80.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .background(MaterialTheme.colorScheme.background)
            .clickable { onClickCard() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        ClickableText(
            AnnotatedString(
                text = location.city,
            ),
            modifier = Modifier.padding(horizontal = 12.dp),
            style = MaterialTheme
                .typography
                .titleLarge.copy(color = MaterialTheme.colorScheme.onSurface)
        ) {
            onShowLocation(location)
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewLocationPickerUnit() {
    JazzyWeatherTheme {

    }
}