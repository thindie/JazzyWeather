package com.example.thindie.designsystem.di

import com.example.thindie.designsystem.DecodeAble
import com.example.thindie.designsystem.utils.WeatherCodeDecoder
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface WeatherCodeDecoderModule {
    @Binds
    fun bindWeatherDecoder(decoder: WeatherCodeDecoder): DecodeAble
}