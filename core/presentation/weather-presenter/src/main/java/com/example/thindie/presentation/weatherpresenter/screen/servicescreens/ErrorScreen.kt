package com.example.thindie.presentation.weatherpresenter.screen.servicescreens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.thindie.presentation.R
import com.example.thindie.presentation.designsystem.textutil.HeadLineText

@Composable
fun ErrorScreen() {
    val currentContext = LocalContext.current
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000,
                easing = FastOutLinearInEasing,
            ),
        )
    )
    Surface(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val sunnyDrawable = ContextCompat
                .getDrawable(currentContext, R.drawable.weather_sunny)

            requireNotNull(sunnyDrawable)
            val bitmapFromDrawable = createBitmap(sunnyDrawable)

            Canvas(modifier = Modifier.size(150.dp)) {

                rotate(rotation) {
                    drawImage(
                        bitmapFromDrawable
                            .asImageBitmap()
                    )
                }
            }
            stringResource(R.string.message_error_screen).HeadLineText()

        }
    }
}

@Preview
@Composable
fun previewErrorScreen() {
    com.example.thindie.presentation.designsystem.theme.JazzyWeatherTheme {
        ErrorScreen()
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun previewErrorScreenNight() {
    com.example.thindie.presentation.designsystem.theme.JazzyWeatherTheme {
        ErrorScreen()
    }
}

private fun createBitmap(drawable: Drawable): Bitmap {
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    return bitmap
}

