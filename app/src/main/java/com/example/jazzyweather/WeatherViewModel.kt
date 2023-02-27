package com.example.jazzyweather

import androidx.lifecycle.ViewModel
import com.example.jazzyweather.domain.useCases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val add: AddFavoriteUseCase,
    private val delete: DeleteFavoriteUseCase,
    private val search: SearchLocationUseCase,
    private val request: RequestWeatherUseCase,
    private val offline: GetOfflineWeatherUseCase,
) : ViewModel() {

}