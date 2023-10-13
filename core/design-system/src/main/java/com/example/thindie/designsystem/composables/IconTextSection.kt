package com.example.thindie.designsystem.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun IconTextSection(
    modifier: Modifier,
    @DrawableRes icon: Int,
    title: String,
    color: Color,
    style: TextStyle = MaterialTheme.typography.labelMedium,
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = modifier.padding(horizontal = 8.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = color
        )
        Text(
            text = title,
            style = style.copy(color)
        )
    }
}