package com.example.thindie.domain.localresourceobserver.usecase

import com.example.thindie.common.DispatchersMainModule
import com.example.thindie.domain.localresourceobserver.contracts.WeatherPlacePresenter
import com.example.thindie.domain.localresourceobserver.entity.PossiblyWeatherLocation
import com.example.thindie.domain.localresourceobserver.repository.WeatherPlaceObserver
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class ObservePlacesUseCase @Inject constructor(
    private val weatherPlaceObserver: WeatherPlaceObserver,
    @DispatchersMainModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher
) :
    WeatherPlacePresenter {
    override fun observePlaces(placeName: String): Flow<PossiblyWeatherLocation> {
        return weatherPlaceObserver.observePlaces(placeName)
            .flowOn(mainDispatcher)
    }
}