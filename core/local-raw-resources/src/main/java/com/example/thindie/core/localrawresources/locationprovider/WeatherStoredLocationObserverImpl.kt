package com.example.thindie.core.localrawresources.locationprovider


import android.app.Application
import com.example.thindie.core.local_raw_resources.R
import com.example.thindie.core.localrawresources.Coordinates
import com.example.thindie.core.localrawresources.LocationNameParser
import com.example.thindie.core.localrawresources.WeatherStoredLocationObserver
import com.example.thindie.core.localrawresources.ldo.LocationPropertiesLdo
import com.example.thindie.core.localrawresources.parserules.CITY
import com.example.thindie.core.localrawresources.parserules.COORDINATES_PATTERN_WITH_POINT
import com.example.thindie.core.localrawresources.parserules.LATITUDE
import com.example.thindie.core.localrawresources.parserules.LONGITUDE
import com.example.thindie.core.localrawresources.parserules.SPLIT_BY_WHITESPACE
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


internal class WeatherStoredLocationObserverImpl @Inject constructor(
    private val application: Application,
    private val locationNameParser: LocationNameParser
) : WeatherStoredLocationObserver {

    override fun getLocationByCoordinates(coordinates: Coordinates): Flow<List<LocationPropertiesLdo>> {
        return filterByConditionsAndParseJsonObjectsAsFlow {
            asJsonObject.checkCoordinateConditions(coordinates)
        }
    }

    override fun getLocationByStringTag(locationTag: String): Flow<List<LocationPropertiesLdo>> {
        return if (locationTag.matches(
                COORDINATES_PATTERN_WITH_POINT.toRegex()
            )
        ) {
            getLocationByCoordinates(locationTag.parseAsCoordinates())
        } else {
            val preparedTagToSearch = locationNameParser.transcryptParseLocationName(locationTag)
            getLocationBySearchCity(preparedTagToSearch)
        }
    }

    private fun getLocationBySearchCity(locationTag: String): Flow<List<LocationPropertiesLdo>> {
        return filterByConditionsAndParseJsonObjectsAsFlow {
            asJsonObject.get(CITY).asString.meetConditions(locationTag)
        }
    }


    @kotlin.jvm.Throws(IllegalArgumentException::class, NumberFormatException::class)
    private fun String.parseAsCoordinates(): Coordinates {
        val tagToParse = this
        tagToParse.split(SPLIT_BY_WHITESPACE)
        return object : Coordinates {
            override val latitude: Float
                get() = requireNotNull(tagToParse[0].toString()).toFloat()
            override val longitude: Float
                get() = requireNotNull(tagToParse[0].toString()).toFloat()
        }
    }

    private fun readResourcesJsonArray(): JsonArray {
        val stream = application.resources.openRawResource(R.raw.ru)
        val array = Gson().fromJson(
            BufferedReader(InputStreamReader(stream)), JsonArray::class.java
        )
        stream.close()
        return array
    }

    private fun JsonObject.checkCoordinateConditions(coordinates: Coordinates): Boolean {
        val isLatitudeLegit = get(LATITUDE).asString.meetConditions(coordinates.latitude.toString())
        val isLongitudeLegit =
            get(LONGITUDE).asString.meetConditions(coordinates.latitude.toString())
        return isLatitudeLegit && isLongitudeLegit
    }


    private fun String.meetConditions(locationTag: String): Boolean {
        return (this.contains(
            locationTag, true
        ) || this == locationTag || locationTag.contains(this, true))
    }

    private fun filterByConditionsAndParseJsonObjectsAsFlow(filterThem: JsonElement.() -> Boolean)
            : Flow<List<LocationPropertiesLdo>> {
        return flow {
            readResourcesJsonArray()
                .asSequence()
                .filter { element ->
                    element.filterThem()
                }
                .map { filteredElement ->
                    val parsedAndAlmostReady =
                        Gson().fromJson(filteredElement, LocationPropertiesLdo::class.java)
                    parsedAndAlmostReady.copy(
                        city = locationNameParser.outcomingTranscryptParseLocationName(
                            parsedAndAlmostReady.city
                        )
                    )
                }
                .toList()
                .apply {
                    emit(this@apply)
                }
        }
    }

}



