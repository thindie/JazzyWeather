package com.example.jazzyweather.ui.composables

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
                    Column(
                        Modifier
                            .basicDimensions(eighty)
                            .eightStartPadding()) {
                        it.place.Body()
                        Row {
                            it.latitude.toString().Label()
                            Spacer(modifier = Modifier.padding(two).size(two))
                            it.longitude.toString().Label()
                        }

                        it.adaptedTimeZone.toString().Label()
                    }

                }
                SpacerTwelve()
            }
        }
    }
}
