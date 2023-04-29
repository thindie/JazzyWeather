package com.example.thindie.core.localrawresources

internal interface LocationNameParser {
    fun transcryptParseLocationName(locationName: String): String
    fun outcomingTranscryptParseLocationName(locationName: String): String
}