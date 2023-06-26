package com.example.thindie.presentation.locationchoser.screen.cardunit

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thindie.presentation.designsystem.textutil.BodyText
import com.example.thindie.presentation.designsystem.textutil.HeadLineText
import com.example.thindie.presentation.designsystem.textutil.LabelMediumText

@Composable
internal fun LocationCardEnd(modifier: Modifier, adminName: String) {
    Column(modifier) {
        adminName.HeadLineText()
    }
}

@Composable
internal fun LocationCardBody(modifier: Modifier, latitude: String, longitude: String) {
    Column(modifier) {
        latitude.BodyText()
        longitude.BodyText()
    }
}

@Composable
internal fun LocationCardStart(modifier: Modifier, city: String, population: String) {
    Column(modifier) {
        city.HeadLineText()
        population.LabelMediumText()
    }
}