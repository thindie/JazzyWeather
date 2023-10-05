package com.example.thindie.location_presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.animators.AlphaAnimator
import com.example.thindie.designsystem.animators.rememberAlphaAnimator
import com.example.thindie.presentation.R

@Composable
internal fun EmptySearchContent(
    modifier: Modifier = Modifier,
    alphaAnimator: AlphaAnimator = rememberAlphaAnimator(
        time = 2700,
        minValue = 0.1f,
    ),

) {
    LaunchedEffect(true) {
        alphaAnimator.animate(this)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = LocationPresenterColors.unitColors,
            )
    ) {
        Icon(
            modifier = modifier.size(128.dp),
            painter = painterResource(
                id = R.drawable.icon_search,
            ),
            tint = LocationPresenterColors.populationValue.copy(alphaAnimator.animatedValue.value),
            contentDescription = null
        )
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewEmptySearchContent() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {
        EmptySearchContent()
    }
}
