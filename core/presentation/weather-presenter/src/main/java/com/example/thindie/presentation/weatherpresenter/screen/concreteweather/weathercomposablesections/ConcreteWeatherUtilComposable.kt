package com.example.thindie.presentation.weatherpresenter.screen.concreteweather

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thindie.presentation.designsystem.textutil.BodyLargeText
import com.example.thindie.presentation.util.TimeParser

@Composable
fun List<String>.BuildDaysLine() {
    val values = PaddingValues(start = 12.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
    OutlinedCard(
        Modifier
            .padding(values)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = ShapeDefaults.ExtraLarge
    ) {
        Row(Modifier.padding(start = 25.dp, end = 8.dp, top = 4.dp)) {

        }
        LazyRow(modifier = Modifier.padding(start = 20.dp, end = 8.dp, bottom = 4.dp)) {
            items(this@BuildDaysLine) {
                TimeParser(it).day.BodyLargeText(
                    modifier = Modifier.padding(start = 11.dp, end = 24.dp)
                )
            }
        }
    }
}

@Composable
fun graph(list: List<Double>, content: @Composable () -> Unit) {
    if (list.any { it != 0.0 }) {
        content()
    }
}