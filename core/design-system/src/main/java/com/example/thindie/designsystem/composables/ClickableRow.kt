package com.example.thindie.designsystem.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.designsystem.NavigationAble
import com.example.thindie.designsystem.navList

@Composable
fun ClickAbleRow(
    modifier: Modifier = Modifier,
    rowColor: Brush,
    contentColor: Color,
    list: List<NavigationAble>,
    onClick: (String) -> Unit,
    clickAbleContent: @Composable () -> Unit,
) {
    Row(
        modifier = modifier
            .background(brush = rowColor)
            .height(50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow(
            modifier = modifier
                .fillMaxWidth(0.6f)
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(list) {
                IconButton(onClick = { onClick(it.getRoute()) }) {
                    Icon(
                        painter = painterResource(id = it.getVision()),
                        contentDescription = it.getRoute(),
                        tint = contentColor
                    )
                }
            }

        }
        clickAbleContent()
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
            onClick = {}, clickAbleContent =  {})
    }
}
