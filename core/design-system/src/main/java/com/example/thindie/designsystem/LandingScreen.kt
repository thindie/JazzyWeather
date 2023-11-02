package com.example.thindie.designsystem

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.designsystem.utils.Gradient

@Composable
fun JazzyLandingScreen(modifier: Modifier = Modifier, duration: Int) {

    var shouldRotate by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true, block = {
        shouldRotate = true
    })

    val alphaState by animateFloatAsState(
        targetValue = if (shouldRotate) 0f else 1f,
        label = "",
        animationSpec = tween(durationMillis = duration, easing = FastOutSlowInEasing)
    )

    Box(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.onSurfaceVariant
                    .Gradient(
                        color1 = MaterialTheme.colorScheme.surfaceTint,
                        color2 = MaterialTheme.colorScheme.primaryContainer
                    )
            )
            .zIndex(1f)
            .fillMaxSize()
    )

    Column(
        Modifier
            .background(MaterialTheme.colorScheme.surface.copy(alphaState))
            .zIndex(2f)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }

}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewJazzyLandingScreen() {
    JazzyWeatherTheme {
        JazzyLandingScreen(duration = 3000)
    }
}