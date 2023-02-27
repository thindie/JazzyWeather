package com.example.jazzyweather

import androidx.lifecycle.ViewModel
import com.example.jazzyweather.domain.useCases.AddFavoriteUseCase
import com.example.jazzyweather.domain.useCases.DeleteFavoriteUseCase
import com.example.jazzyweather.domain.useCases.RequestWeatherUseCase
import com.example.jazzyweather.domain.useCases.SearchLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val add: AddFavoriteUseCase,
    private val delete: DeleteFavoriteUseCase,
    private val search: SearchLocationUseCase,
    private val request: RequestWeatherUseCase,
) : ViewModel() {

}