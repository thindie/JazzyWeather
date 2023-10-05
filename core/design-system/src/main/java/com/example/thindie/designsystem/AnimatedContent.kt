package com.example.thindie.designsystem

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedContent(
    modifier: Modifier = Modifier,
    transition: InfiniteTransition = rememberInfiniteTransition(),
    firstDrop: AnimatedContentState = rememberAnimatedContent(xStart = 0f, duration = 300),
    secondDrop: AnimatedContentState = rememberAnimatedContent(xStart = -30f, duration = 400),
    thirdDrop: AnimatedContentState = rememberAnimatedContent(
        xStart = 30f,
        duration = 500
    ),
    tint: Color,
    dropsColor: Color = tint,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        val xFirst = firstDrop.x
        val yFirst = firstDrop.y

        val xSecond = secondDrop.x
        val ySecond = secondDrop.y

        val xThird = thirdDrop.x
        val yThird = thirdDrop.y

        val size = transition.animateValue(
            initialValue = 72.dp,
            targetValue = 192.dp,
            typeConverter = Dp.VectorConverter,
            animationSpec = infiniteRepeatable(tween(6200, easing = FastOutLinearInEasing)),
            label = this::class.java.name
        )


        Column {
            Icon(
                modifier = Modifier.size(size.value),
                painter = painterResource(
                    id =
                    com.example.thindie.presentation.R.drawable.anim_icon_cloud
                ),
                contentDescription = null,
                tint = tint
            )
        }
        Column {
            Canvas(
                modifier = Modifier
                    .wrapContentSize()
            ) {
                RainLine(
                    x = xFirst, y = yFirst, xEnd = firstDrop.xEnd, yEnd = firstDrop.yEnd,
                    strokeWidth = 9f,
                    color = dropsColor
                )
                RainLine(
                    x = xSecond, y = ySecond, xEnd = secondDrop.xEnd, yEnd = secondDrop.yEnd,
                    strokeWidth = 9f,
                    color = dropsColor
                )
                RainLine(
                    x = xThird, y = yThird, xEnd = thirdDrop.xEnd, yEnd = thirdDrop.yEnd,
                    strokeWidth = 9f,
                    color = dropsColor
                )
            }
        }
    }
}


internal fun DrawScope.RainLine(
    x: State<Float>,
    y: State<Float>,
    xEnd: Float,
    yEnd: Float,
    strokeWidth: Float,
    color: Color,
) {


    val offsetStart = Offset(x.value, y.value)
    val offsetEnd = Offset(xEnd, yEnd)
    drawLine(
        color = color,
        offsetStart,
        offsetEnd,
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewAnimatedContent() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {
        AnimatedContent(
            tint = Color.Cyan,
            dropsColor = Color.Blue
        )
    }
}