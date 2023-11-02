package com.example.thindie.designsystem.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.random.Random


@Composable
fun VerticalIndicationColumn(
    state: VerticalColumnStateHolder,
) {

    val shouldAnimate = remember {
        mutableStateOf(false)
    }

    val height =
        if (!shouldAnimate.value) 0f
        else state.customizer.getShapeComponent()


    LaunchedEffect(true) {
        delay(Random.nextLong(until = 1000))
        shouldAnimate.value = true
    }

    val modifier: Modifier = Modifier
        .width(state.width)
        .heightIn(max = state.width * 4)



    Column(
        modifier = modifier.wrapContentHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AnimatedVisibility(visible = shouldAnimate.value) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(height)
                        .clip(RoundedCornerShape(10.dp))
                        .background(state.customizer.getColorComponent())
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
        }
        Text(
            text = state.textLabel,
            style = MaterialTheme.typography.labelMedium
        )
    }
}
