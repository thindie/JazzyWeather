package com.example.thindie.domain.localresourceobserver.usecase

import android.util.Log
import com.example.thindie.common.DispatchersMainModule
import com.example.thindie.domain.localresourceobserver.contracts.WeatherPlacePresenter
import com.example.thindie.domain.localresourceobserver.entity.PossiblyWeatherLocation
import com.example.thindie.domain.localresourceobserver.repository.WeatherPlaceObserver
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class ObservePlacesUseCase @Inject constructor(
    private val weatherPlaceObserver: WeatherPlaceObserver,
    @DispatchersMainModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher
) :
    WeatherPlacePresenter {
    override fun presentPlaces(possiblyLocationName: String): Flow<List<PossiblyWeatherLocation>> {
        Log.d("SERVICE_TAG_USECASE",possiblyLocationName)
        return weatherPlaceObserver.observePlaces(possiblyLocationName)
            .flowOn(mainDispatcher)
    }
}