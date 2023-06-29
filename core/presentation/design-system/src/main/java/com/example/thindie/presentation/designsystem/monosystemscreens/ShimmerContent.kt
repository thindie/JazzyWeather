package com.example.thindie.presentation.designsystem.monosystemscreens

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.thindie.presentation.designsystem.theme.Shapes

@Composable
fun rememberShimmerStateScreenDecoration(
    routeToBeShimmered: String,
    isWideScreenSize: Boolean = false,
    transition: InfiniteTransition = rememberInfiniteTransition()
): ShimmerState {
    return remember(routeToBeShimmered, isWideScreenSize, transition) {
        ShimmerState(
            routeToBeShimmered,
            isWideScreenSize,
            transition
        )
    }
}

@Stable
class ShimmerState(
    private val routeToBeShimmered: String,
    private val isWideScreenSize: Boolean,
    private val transition: InfiniteTransition
) {
    private val color
        @Composable get() = MaterialTheme.colorScheme.surfaceVariant
    private val shimmersColorsList
        @Composable get() = listOf(
            color.copy(0.6f),
            color.copy(0.2f),
            color.copy(0.6f)
        )

    private val translateAnimation
        @Composable get() = transition.animateFloat(
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

    private val brush
        @Composable get() = Brush.linearGradient(
            colors = shimmersColorsList,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
    val Screen
        @Composable get() = when (routeToBeShimmered) {

            else -> LinearProgressIndicator()
        }
}

@Composable
internal fun ShimmeringList(brush: Brush) {
    LazyColumn(modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)) {
        items(9) {
            ForListScreensElement(brush = brush)
        }
    }
}

@Composable
internal fun ShimmeringNews(brush: Brush, isWideScreenSize: Boolean) {
    LazyColumn() {
        items(3) {
            ForNewsScreenElement(brush = brush, isWideScreenSize = isWideScreenSize)
        }
    }
}

@Composable
private fun ForListScreensElement(brush: Brush, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
            .background(color = MaterialTheme.colorScheme.surface)
            .height(80.dp)
    ) {
        Column(
            modifier.weight(0.2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            RoundShimmer(brush = brush)
        }
        Column(
            modifier.weight(0.8f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Row(modifier.padding(all = 10.dp)) {
                LineShimmer(brush)
            }
            Row(modifier.padding(all = 10.dp)) {
                LineShimmer(
                    brush = brush,
                    modifier = modifier
                        .fillMaxWidth(0.7f)
                        .height(10.dp)
                )
            }
        }
    }
}

@Composable
private fun ForNewsScreenElement(brush: Brush, isWideScreenSize: Boolean) {
    Spacer(modifier = Modifier.padding(top = 20.dp))
    if (!isWideScreenSize) {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            NewsShimmerHeader(brush)
            NewsShimmerLinesDecorator(brush)
        }
    } else {
        Row(
            Modifier
                .fillMaxHeight()
                .wrapContentWidth()
        ) {
            NewsShimmerHeader(brush)
            NewsShimmerLinesDecorator(brush)
        }
    }
}

@Composable
private fun NewsShimmerHeader(brush: Brush, modifier: Modifier = Modifier) {
    val values = PaddingValues(start = 2.dp, end = 8.dp)
    Row(
        modifier
            .height(300.dp)
            .width(400.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier
                .fillMaxHeight()
                .fillMaxWidth(0.2f)
                .padding(values),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RoundShimmer(
                brush = brush,
                modifier
                    .size(40.dp)
                    .padding(bottom = 8.dp)
            )
            RoundShimmer(
                brush = brush,
                modifier
                    .size(40.dp)
                    .padding(top = 8.dp)
            )
        }
        Column(
            modifier
                .fillMaxHeight()
                .fillMaxWidth(0.8f)
                .padding(values),
        ) {
            LineShimmer(
                brush = brush,
                modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.8f)
                    .padding(all = 2.dp)
            )
        }
    }
}

@Composable
private fun NewsShimmerLinesDecorator(
    brush: Brush,
    modifier: Modifier = Modifier,
    floatLengthMultipliers: List<Float> = listOf(
        0.3f,
        0.7f,
        0.5f,
        0.3f,
        0.4f
    )
) {
    val heightOfShimmeredLine = 40.dp
    val padding = PaddingValues(top = 4.dp, bottom = 4.dp, start = 3.dp, end = 3.dp)
    Row(
        modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(start = 20.dp)
    ) {
        LazyColumn() {
            items(floatLengthMultipliers) { length ->
                Row(
                    modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(padding)
                ) {
                    LineShimmer(
                        brush = brush,
                        modifier
                            .fillMaxWidth(length)
                            .height(heightOfShimmeredLine)
                            .padding(padding)
                    )
                    LineShimmer(
                        brush = brush,
                        modifier
                            .fillMaxWidth(1f - length)
                            .height(heightOfShimmeredLine)
                            .padding(padding)
                    )
                }
            }
        }
    }
}

@Composable
private fun RoundShimmer(brush: Brush, modifier: Modifier = Modifier) {
    Spacer(
        modifier = modifier
            .size(70.dp)
            .clip(CircleShape)
            .background(brush)
    )
}

@Composable
private fun LineShimmer(brush: Brush, modifier: Modifier = Modifier) {
    Spacer(
        modifier = modifier
            .fillMaxWidth(0.6f)
            .height(15.dp)
            .clip(Shapes.extraLarge)
            .background(brush)
    )
}
