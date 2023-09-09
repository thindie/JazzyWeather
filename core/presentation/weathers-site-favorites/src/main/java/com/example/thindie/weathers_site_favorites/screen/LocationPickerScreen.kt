package com.example.thindie.weathers_site_favorites.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thindie.designsystem.composables.inputfield.InputFieldState
import com.example.thindie.designsystem.composables.inputfield.rememberInputFieldState
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.weathers_site_favorites.viewmodel.WeatherFavoritesViewModel

@Composable
internal fun WeatherFavoritesScreen(
    modifier: Modifier = Modifier,
    inputFieldState: InputFieldState = rememberInputFieldState(),
    viewModel: WeatherFavoritesViewModel = hiltViewModel(),
) {

}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherFavoritesScreen() {
    JazzyWeatherTheme {

    }
}

