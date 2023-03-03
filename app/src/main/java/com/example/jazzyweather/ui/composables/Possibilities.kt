package com.example.jazzyweather.ui.composables

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.ui.composables.util.*

@Composable
fun PossibilitiesList(onClick: (Possibility) -> Unit, possibility: List<Possibility>) {
    Log.d("SERVICE_TAG", "$possibility")
    OnScreen {
        Spacer(
            modifier = Modifier
                .padding(top = twelve)
                .size(two)
        )

        Spacer(
            modifier = Modifier
                .padding(top = twelve)
                .size(two)
        )
        LazyColumn(
            modifier = Modifier
                .basicDimensions()
                .fillMaxHeight()
        ) {
            items(possibility) {
                OutlinedCard(
                    Modifier
                        .basicDimensions()
                        .clickable { onClick(it) },
                    shape = ShapeDefaults.ExtraLarge,
                    elevation = CardDefaults.cardElevation(defaultElevation = two),
                    border = BorderStroke(two, color = Color.Transparent)
                ) {
                    Column(
                        Modifier
                            .basicDimensions(eighty)
                            .padding(start = thirty)
                    ) {
                        it.place.Body()
                        Row(Modifier.padding(top = two)) {
                            it.latitude.toString().Label()
                            Spacer(
                                modifier = Modifier
                                    .padding(two)
                                    .size(two)
                            )
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
