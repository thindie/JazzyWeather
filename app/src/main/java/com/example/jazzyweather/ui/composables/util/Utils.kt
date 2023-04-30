package com.example.jazzyweather.ui.composables.util

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.presentation.designsystem.theme.md_theme_dark_error
import com.example.thindie.presentation.designsystem.theme.md_theme_dark_primary
import com.example.thindie.presentation.designsystem.theme.md_theme_light_error
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ountlinedCards() =
    CardDefaults.outlinedCardColors(
        contentColor = color().onSurface,
        containerColor = color().surface,
        disabledContainerColor = color().onSurface,
        disabledContentColor = color().surface
    )

@Composable
fun typo() = MaterialTheme.typography

@Composable
fun color() = MaterialTheme.colorScheme

@Composable
fun icons() = Icons.Default

fun Modifier.basicDimensions(height: Dp = eighty) =
    run {
        this
            .fillMaxWidth()
            .height(height)
            .padding(start = eight, end = twelve)

    }

@Composable
fun OnScreen(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Surface(modifier = modifier.fillMaxSize(), color = color().surface) {
        content()
    }
}


fun Modifier.surfaceColor() = composed { this.background(color().surface) }
fun Modifier.onSurfaceColor() = composed { this.background(color().onSurface) }
fun Modifier.eightStartPadding() = run { this.padding(start = eight) }
fun Modifier.twelveEndPadding() = run { this.padding(end = twelve) }

val Al_CV = Alignment.CenterVertically
val Ar_C = Arrangement.Center
val Al_H = Alignment.CenterHorizontally

@Composable
fun String.Display(modifier: Modifier = Modifier, color: Color = color().onSurface) {
    modifier.eightStartPadding()
    Text(modifier = modifier, text = this, style = typo().headlineLarge, color = color)
}

@Composable
fun String.Title(modifier: Modifier = Modifier, color: Color = color().onSurface) {
    modifier.eightStartPadding()
    Text(modifier = modifier, text = this, style = typo().headlineMedium, color = color)
}

@Composable
fun String.Headline(modifier: Modifier = Modifier, color: Color = color().onSurface) {
    modifier.eightStartPadding()
    Text(modifier = modifier, text = this, style = typo().headlineSmall, color = color)
}

@Composable
fun String.Body(modifier: Modifier = Modifier, color: Color = color().onSurface) {
    modifier.eightStartPadding()
    Text(modifier = modifier, text = this, style = typo().bodyLarge, color = color)
}

@Composable
fun String.Label(modifier: Modifier = Modifier, color: Color = color().onSurface) {
    modifier.eightStartPadding()
    Text(modifier = modifier, text = this, style = typo().labelMedium, color = color)
}

@Composable
fun String.LabelBold(modifier: Modifier = Modifier, color: Color = color().onSurface) {
    modifier.eightStartPadding()
    Text(modifier = modifier, text = this, style = typo().labelMedium, color = color)
}


@Composable
fun SpacerTwelve(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(twelve))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textFieldColors() = TextFieldDefaults.outlinedTextFieldColors(
    textColor = color().onSurface,
    unfocusedLabelColor = color().surface,
    focusedLabelColor = color().onSurface,
    unfocusedBorderColor = color().surface,
    focusedBorderColor = color().onSurface
)


@Composable
fun HourlyCard(list: List<Double>, @StringRes label: Int) {

    OutlinedCard(
        Modifier
            .width(extraWide)
            .height(hundred)
            .eightStartPadding()
            .twelveEndPadding()
            .padding(bottom = twelve),
        shape = ShapeDefaults.ExtraLarge,

        colors = ountlinedCards(),
        elevation = CardDefaults.cardElevation(defaultElevation = two),
        border = BorderStroke(two, color = Color.Transparent)
    ) {
        Column(Modifier.padding(start = thirty, end = twelve)) {
            stringResource(id = label)
                .Headline(color = color().onSurfaceVariant)
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.height(sixty)
            ) {


                items(DAY_IN_HOURS) { hour ->
                    Column {
                        hour
                            .toString()
                            .plus(HOURS)
                            .plus("\n")
                            .plus(list[hour])
                            .Body(
                                modifier = Modifier.padding(end = eight),
                                color =  when (list[hour]
                                    ) {
                                    in PLUS -> { if(isSystemInDarkTheme()) com.example.thindie.presentation.designsystem.theme.md_theme_dark_error
                                    else com.example.thindie.presentation.designsystem.theme.md_theme_light_error.copy(0.7f) }
                                    ZERO -> { color().onPrimaryContainer }
                                    in MINUS -> {
                                        if (isSystemInDarkTheme()) com.example.thindie.presentation.designsystem.theme.md_theme_dark_primary
                                        else Color.Blue
                                    }
                                    else -> Color.Red
                                }
                            )
                    }
                }

            }
        }
    }
}

private const val HOURS = ":00"
private const val DAY_IN_HOURS = 24

val no = 0.dp
val hair = Dp.Hairline
val two = 2.dp
val eight = 8.dp
val twelve = 12.dp
val twenty = 20.dp
val thirty = 30.dp
val fourty = 40.dp
val fifty = 50.dp
val sixty = 60.dp
val eighty = 80.dp
val hundred = 100.dp
val hundredTwenty = 120.dp
val extraWide = 280.dp
val toScreen = 350.dp
private val PLUS = 0.1..17.0
private val MINUS = -100.0..0.1
private const val ZERO = 0.0

@Composable
fun LoadingContent(
    isLoading: Boolean,
    isEmpty: Boolean,
    emptyContent: @Composable (modifier: Modifier) -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    if (isEmpty) {
        emptyContent(modifier)
    } else {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isLoading),
            onRefresh = onRefresh,
            content = content,
        )
    }
}