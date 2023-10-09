package com.example.jazzyweather.loadScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thindie.designsystem.animators.AlphaAnimator
import com.example.thindie.designsystem.animators.rememberAlphaAnimator
import com.example.thindie.designsystem.composables.IconTextSection
import com.example.thindie.presentation.R
import kotlinx.coroutines.delay

@Composable
internal fun LoadingScreen(
    time: Int,
    modifier: Modifier = Modifier,
    animator: AlphaAnimator = rememberAlphaAnimator(
        time = time,
        minValue = 0.2f,
    ),
    content: @Composable () -> Unit,
) {
    val shouldShowContent = remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        animator.animate(this)
        delay(time.toLong())
        shouldShowContent.value = true
    }
    if (shouldShowContent.value) {
        content()
    } else {
        Spacer(modifier = modifier.height(80.dp))
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LoadingColors.titleColors, alpha = animator.animatedValue.value),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(verticalArrangement = Arrangement.Center) {
                items(values) {
                    IconTextSection(
                        modifier = modifier.wrapContentSize(),
                        icon = it.second,
                        title = it.first,
                        style = MaterialTheme.typography.displayLarge.copy(
                            MaterialTheme.colorScheme.surface.copy(alpha = animator.animatedValue.value),
                            fontSize = 102.sp
                        )
                    )
                }

            }
            LinearProgressIndicator(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth(), strokeCap = StrokeCap.Square
            )
        }
    }
}


val values = listOf(
    "ЯС" to R.drawable.icon_windy,
    "НО" to R.drawable.nav_icon_favorite,
    "ПОНЯТ" to R.drawable.nav_icon_search,
    "HO" to R.drawable.icon_temp_high
)
