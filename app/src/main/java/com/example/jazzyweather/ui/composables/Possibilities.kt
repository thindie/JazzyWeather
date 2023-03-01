package com.example.jazzyweather.ui.composables

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.ui.composables.util.*

@Composable
fun PossibilitiesList(onClick: (Possibility) -> Unit, possibility: List<Possibility>) {
    Log.d("SERVICE_TAG", "$possibility")
    OnScreen() {
        LazyColumn(
            modifier = Modifier
                .basicDimensions()
                .fillMaxHeight()
        ) {
            items(possibility) {
                ElevatedCard(
                    Modifier
                        .basicDimensions()
                        .clickable { onClick(it) }) {
                    it.place.Body()
                    it.latitude.toString().Label()
                    it.longitude.toString().Label()
                    it.timeZone.toString().Label()
                }
                SpacerTwelve()
            }
        }
    }
}
