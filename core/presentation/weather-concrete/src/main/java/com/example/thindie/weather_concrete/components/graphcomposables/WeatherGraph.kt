package com.example.thindie.weather_concrete.components.graphcomposables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.composables.IconTextSection
import com.example.thindie.designsystem.composables.VerticalIndicationColumn
import com.example.thindie.designsystem.composables.rememberVerticalColumnState
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.designsystem.utils.TransGradientVertical
import com.example.thindie.designsystem.utils.toVisualCustomizersList
import kotlin.math.floor

@Composable
internal fun WeatherGraph(
    modifier: Modifier = Modifier,
    weatherGraphState: WeatherGraphState,
) {
    val customizedList = weatherGraphState.list.toVisualCustomizersList(
        weatherGraphState.firstColorComponent,
        weatherGraphState.secondColorComponent
    )
    Column(
        modifier = Modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(brush = MaterialTheme.colorScheme.primaryContainer.TransGradientVertical())
            .wrapContentSize()
    ) {
        IconTextSection(
            modifier = Modifier.padding(all = 12.dp),
            icon = weatherGraphState.graphIcon,
            title = stringResource(weatherGraphState.title),
            color =weatherGraphState.iconTint,
            style = MaterialTheme.typography.titleLarge
        )
        LazyRow(
            modifier = modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            itemsIndexed(customizedList) { i, customizer ->
                VerticalIndicationColumn(
                    rememberVerticalColumnState(
                        width = 40.dp,
                        customizer = customizer,
                        textLabel = weatherGraphState.list[i].toString()
                    )
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherGraph() {
    JazzyWeatherTheme {

    }
}
