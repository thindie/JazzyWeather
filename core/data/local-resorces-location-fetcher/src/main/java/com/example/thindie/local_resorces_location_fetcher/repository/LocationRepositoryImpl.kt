package com.example.thindie.local_resorces_location_fetcher.repository

import com.example.thindie.core.localrawresources.LocateAble
import com.example.thindie.core.localrawresources.WeatherStoredLocationObserver
import com.example.thindie.domain.LocationRepository
import com.example.thindie.domain.entities.WeatherLocation
import com.example.thindie.local_resorces_location_fetcher.mapper.map
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class LocationRepositoryImpl @Inject constructor(private val locationObserver: WeatherStoredLocationObserver) :
    LocationRepository {
    override fun observeLocationCurrentRequest(currentRequest: String): Flow<List<WeatherLocation>> {
        return if (!currentRequest.isCoordinates()) {
            onRequestTitle(currentRequest)
        } else onRequestCoordinates(currentRequest.toAnonymeLocateAble())
    }

    private fun onRequestTitle(currentRequest: String): Flow<List<WeatherLocation>> {
        return locationObserver.getLocationByStringTag(currentRequest)
            .map { list -> list.map { it.map() } }
    }

    private fun onRequestCoordinates(locateAble: LocateAble): Flow<List<WeatherLocation>> {
        return locationObserver.getLocationByCoordinates(locateAble)
            .map { list -> list.map { it.map() } }
    }

    private fun String.isCoordinates() = matches(COORDINATES_PATTERN.toRegex())

    private fun String.toAnonymeLocateAble(): LocateAble {
        return object : LocateAble {

            val parsedStrings = split(" ")
                .map {
                    try {
                        it.toFloat()
                    } catch (e: NumberFormatException) {
                        0.0F
                    }
                }
            override val latitude: Float
                get() = parsedStrings[0]
            override val longitude: Float
                get() = parsedStrings[1]
        }
    }

    companion object {
        private const val COORDINATES_PATTERN = "^\\d{2}([.]\\d*)?\\s+\\d{2}([.]\\d*)?\$"
    }
}