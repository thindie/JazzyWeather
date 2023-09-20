package com.example.thindie.weather_concrete.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.StringHelper
import com.example.thindie.designsystem.animators.ExpandAnimator
import com.example.thindie.designsystem.composables.inputfield.InputField
import com.example.thindie.designsystem.composables.inputfield.InputFieldState
import com.example.thindie.designsystem.composables.inputfield.rememberInputFieldState
import kotlinx.coroutines.CoroutineScope

@Composable
internal fun rememberOperationsPlanchetteState(
    scope: CoroutineScope = rememberCoroutineScope(),
    title: String,
    timeZone: String,
)
        : OperationsPlanchetteState {
    return remember() {
        OperationsPlanchetteState(
            coroutineScope = scope, title = title,
            timeZone = timeZone,
        )
    }
}

@Stable
internal class OperationsPlanchetteState(
    animationTime: Int = 1000,
    private val _height: Dp = 80.dp,
    private val _expanded: Dp = 200.dp,
    coroutineScope: CoroutineScope,
    private val title: String,
    private val timeZone: String,
) : ExpandAnimator(
    animationTime = animationTime,
    initialHeight = _height
) {
    private val _timeZone = mutableStateOf(timeZone)
    private val _title = mutableStateOf(title)

    private val _actionState = mutableStateOf(Action.NONE)
    private val actionState: State<Action>
        get() = _actionState

    val content
        @Composable get() = when (actionState.value) {
            Action.NONE -> {
                Spacer(modifier = Modifier)
            }

            Action.EDIT -> {
                EditSection(
                    timeZone = timeZone,
                    place = title,
                    onClickDismiss = {
                        onDecideAction()
                        _timeZone.value = timeZone
                        _title.value = title
                    },
                    onClickConfirm = { currentTitle, currentTimeZone ->
                        _timeZone.value = currentTimeZone
                        _title.value = currentTitle
                    }
                )
            }

            Action.DELETE -> {

            }
        }


    fun onClickSomeAction(action: Action) {
        _shouldExpand.value = true
        _heightValue.value = _expanded
    }

    fun onDecideAction() {
        _shouldExpand.value = false
        _heightValue.value = _height
    }

    enum class Action {
        DELETE, EDIT, NONE
    }

}

@Composable
private fun EditSection(
    place: String = "",
    timeZone: String = "",
    placeChangeFieldState: InputFieldState = rememberInputFieldState(
        helper = StringHelper.Line(
            place
        )
    ),
    timeZoneInputFieldState: InputFieldState = rememberInputFieldState(
        helper = StringHelper.Line(
            timeZone
        )
    ),
    onClickConfirm: (String, String) -> Unit,
    onClickDismiss: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(com.example.thindie.presentation.R.string.text_field_edit))
        Row {
            InputField(state = placeChangeFieldState)
        }
        Row {
            InputField(state = timeZoneInputFieldState)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = stringResource(com.example.thindie.presentation.R.string.text_field_edit))
            }
            Button(onClick = { /*TODO*/ }) {

            }
        }
    }
}

@Composable
@Preview(showBackground = true)
internal fun preViewEditSection() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {
        EditSection(onClickConfirm = { _, _ -> }) {

        }
    }
}