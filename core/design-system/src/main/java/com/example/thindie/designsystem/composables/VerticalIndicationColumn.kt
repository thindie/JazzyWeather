package com.example.thindie.designsystem.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.customizerFullHeight
import com.example.thindie.designsystem.customizerLessHeight

@Composable
fun VerticalIndicationColumn(width: Dp = 20.dp, customizer: VisualCustomizer) {
    val modifier: Modifier = Modifier
        .width(width)
        .height(width * 3)
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Bottom
        ) {

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(customizer.getShapeComponent())
                    .clip(RoundedCornerShape(10.dp))
                    .background(customizer.getColorComponent())
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewVerticalIndicationColumn() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {
        Column {
            Row {
                VerticalIndicationColumn(
                    width = 10.dp,
                    customizer = customizerFullHeight
                )
                VerticalIndicationColumn(
                    width = 10.dp,
                    customizerLessHeight
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                VerticalIndicationColumn(
                    width = 40.dp,
                    customizer = customizerFullHeight
                )
                VerticalIndicationColumn(
                    width = 40.dp,
                    customizerLessHeight
                )
            }
        }

    }
}