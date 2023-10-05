package com.example.thindie.designsystem.composables

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
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.VisualCustomizer
import com.example.thindie.designsystem.animators.FloatAnimator


@Composable
fun VerticalIndicationColumn(
    width: Dp = 20.dp,
    customizer: VisualCustomizer,
    animator: FloatAnimator? = null,
    textLabel: String = "",
) {

    val modifier: Modifier = Modifier
        .width(width)
        .heightIn(max = width * 4)

    val maxHeight = animator?.animatedValue?.value ?: customizer.getShapeComponent()

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
                    .fillMaxHeight(maxHeight)
                    .clip(RoundedCornerShape(10.dp))
                    .background(customizer.getColorComponent())
            )
            Spacer(modifier = Modifier.size(4.dp))

        }
        Text(
            text = textLabel,
            style = MaterialTheme.typography.bodyLarge.copy(
                MaterialTheme.colorScheme.onSurface
            )
        )
    }
}
