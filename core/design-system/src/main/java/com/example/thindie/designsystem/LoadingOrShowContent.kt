package com.example.thindie.designsystem

import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.composables.VerticalIndicationColumn
import com.example.thindie.designsystem.utils.TransGradientVertical
import com.example.thindie.designsystem.utils.TransGradientVerticalInverse
import com.example.thindie.designsystem.utils.toVisualCustomizersList


@Composable
fun LoadingOrShowContent(
    modifier: Modifier = Modifier,
    state: LoadingOrShowContentState = rememberAnimatedContent(),
    transition: InfiniteTransition = rememberInfiniteTransition(),
    tint: Color,
    dropsColor: Color = tint,
    isLoading: Boolean,
    content: @Composable () -> Unit,
) {
    val titleColors = if (isSystemInDarkTheme()) {
        MaterialTheme
            .colorScheme
            .surface.TransGradientVertical(MaterialTheme.colorScheme.primary)
    } else {
        MaterialTheme.colorScheme.primary.TransGradientVerticalInverse(MaterialTheme.colorScheme.surface)
    }
    if (isLoading) {

        Column(
            modifier = modifier
                .background(titleColors)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            val size = transition.animateValue(
                initialValue = 72.dp,
                targetValue = 192.dp,
                typeConverter = Dp.VectorConverter,
                animationSpec = infiniteRepeatable(tween(7000)),
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
                LazyRow() {
                    items(
                        state.rain.toVisualCustomizersList(
                            positiveColor = dropsColor,
                            negativeColor = dropsColor
                        )
                    ) {
                        Spacer(modifier = Modifier.width(2.dp))
                        VerticalIndicationColumn(customizer = it, width = 5.dp)
                    }

                }
            }
        }
    } else content()
}

