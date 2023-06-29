package com.example.thindie.presentation.weatherpresenter.screen.concreteweather.weathercomposablesections

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thindie.presentation.R
import com.example.thindie.presentation.designsystem.textutil.BodyText

@Composable
fun CoordinatesSection(
    modifier: Modifier = Modifier,
    longitude: Float,
    latitude: Float,
) {
    val values = PaddingValues(all = 5.dp)
    Row(modifier = modifier.wrapContentHeight()) {

        Card(
            shape = CircleShape,
            modifier = modifier
                .padding(values)
                .wrapContentSize()
        ) {
            Row(modifier.padding(start = 10.dp, end = 10.dp, top = 4.dp)) {
                stringResource(id = R.string.latitude, latitude)
                    .BodyText(
                        modifier
                            .padding(values)
                    )

            }
            Row(modifier.padding(start = 10.dp, end = 10.dp, bottom = 4.dp)) {
                stringResource(id = R.string.longitude, longitude)
                    .BodyText(
                        modifier
                            .padding(values)
                    )
            }
        }
    }


}