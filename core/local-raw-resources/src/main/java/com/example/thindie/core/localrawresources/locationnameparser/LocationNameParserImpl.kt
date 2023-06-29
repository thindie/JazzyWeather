package com.example.thindie.core.localrawresources.locationnameparser

import com.example.thindie.core.localrawresources.LocationNameParser
import javax.inject.Inject

internal class LocationNameParserImpl @Inject constructor(): LocationNameParser {
    override fun transcryptParseLocationName(locationName: String): String {
       return locationName.returnCapitalNameOrTranscryptInstead()
    }

    override fun outcomingTranscryptParseLocationName(locationName: String): String{
            return locationName.capitalizeFirstCharsAndTranscrypt()
    }

}