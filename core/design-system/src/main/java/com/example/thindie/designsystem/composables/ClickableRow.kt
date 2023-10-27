package com.example.thindie.designsystem.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.NavigationAble
import com.example.thindie.designsystem.navList
import com.example.thindie.designsystem.theme.JazzyWeatherTheme

private val padding = 100.dp

@Composable
fun ClickAbleRow(
    modifier: Modifier = Modifier,
    rowColor: Brush,
    contentColor: Color,
    list: List<NavigationAble>,
    onClick: (String) -> Unit,
    clickAbleContent: @Composable (Modifier) -> Unit,
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 20.dp, start = 20.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        repeat(list.size) {
            FloatingActionButton(
                onClick = { onClick(list[it].getRoute()) },
                modifier = modifier
                    .size(36.dp)
                    .offset(x = padding * it),
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Icon(
                    painter = painterResource(id = list[it].getVision()),
                    contentDescription = list[it].getRoute(),
                    tint = contentColor
                )
            }
        }
        clickAbleContent(
            modifier
                .size(36.dp)
                .offset(x = padding * list.size)
        )
    }

}


@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewClickAbleRow() {
    JazzyWeatherTheme {
        ClickAbleRow(
            rowColor = Brush.verticalGradient(listOf(Color.White, Color.Blue)),
            contentColor = Color.White,
            list = navList,
            onClick = {}, clickAbleContent = {})
    }
}
