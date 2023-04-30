package com.example.thindie.data.localresourceobserver.repositoryimpl


import com.example.thindie.core.localrawresources.WeatherStoredLocationObserver
import com.example.thindie.data.localresourceobserver.mappers.toPossiblyWeatherLocation
import com.example.thindie.domain.localresourceobserver.entity.PossiblyWeatherLocation
import com.example.thindie.domain.localresourceobserver.repository.WeatherPlaceObserver
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
@Singleton
internal class WeatherPlaceObserverImpl @Inject constructor(
    private val provider: WeatherStoredLocationObserver
) : WeatherPlaceObserver {
    override fun observePlaces(placeName: String): Flow<PossiblyWeatherLocation> {
        return provider.getLocationByStringTag(placeName).map { locationPropertiesLdo ->
            locationPropertiesLdo.toPossiblyWeatherLocation()
        }
    }
}