package com.example.thindie.designsystem.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
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
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.VisualCustomizer
import kotlinx.coroutines.delay
import kotlin.random.Random


@Composable
fun VerticalIndicationColumn(
    width: Dp,
    customizer: VisualCustomizer,
    textLabel: String = "",
) {

    val shouldAnimate = remember {
        mutableStateOf(false)
    }

    val height =
        if (!shouldAnimate.value) 0f
        else customizer.getShapeComponent()


    LaunchedEffect(true) {
        delay(Random.nextLong(until = 400))
        shouldAnimate.value = true
    }

    val modifier: Modifier = Modifier
        .width(width)
        .heightIn(max = width * 4)



    Column(
        modifier = modifier.wrapContentHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(height)
                    .animateContentSize(animationSpec = spring())
                    .clip(RoundedCornerShape(10.dp))
                    .background(customizer.getColorComponent())
            )
            Spacer(modifier = Modifier.size(4.dp))

        }
        Text(
            text = textLabel,
            style = MaterialTheme.typography.labelMedium.copy(
                MaterialTheme.colorScheme.onSurface
            )
        )
    }
}
