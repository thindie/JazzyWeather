package com.example.jazzyweather.domain.useCases

import com.example.jazzyweather.di.DispatchersModule
import com.example.jazzyweather.domain.JazzyWeatherRepository
import com.example.jazzyweather.domain.WeatherOffline
import com.example.jazzyweather.domain.abstractions.Results
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetOfflineWeatherUseCase @Inject constructor(
    private val jazzy: JazzyWeatherRepository,
    @DispatchersModule.IODispatcher private val IO: CoroutineDispatcher,
) {
    operator fun invoke(): Flow<Results<List<WeatherOffline>>> {
        return jazzy.getOfflineWeathers().flowOn(IO)
    }
}