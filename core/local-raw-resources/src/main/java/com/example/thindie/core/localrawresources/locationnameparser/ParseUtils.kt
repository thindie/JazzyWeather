package com.example.thindie.core.localrawresources.locationnameparser

import com.example.thindie.core.localrawresources.parserules.SPLIT_BY_WHITESPACE
import com.example.thindie.core.localrawresources.parserules.fewSymbolsMapTranscryptions
import com.example.thindie.core.localrawresources.parserules.singleSymbolMapTranscrypt

private const val SOMETHING_THAT_DONT_FIND = "543534rsfdslferqfseiporw;.dbaweqw432rf"
@Suppress("ComplexCondition", "ReturnCount")
internal fun String.returnCapitalNameOrTranscryptInstead(): String {
    if (this.isBlank()) return SOMETHING_THAT_DONT_FIND
    return if ("Moskva".contains(this, true)
        || "Москва".contains(this, true)
    ) {
        return "Moscow"
    } else if (
        "Peterburg".contains(this, true)
        || "SanktPeterburg".contains(this, true)
        || "Piter".contains(this, true)
        || "Питер Петербург Санкт".contains(this, true)
    ) {
        ; return "Saint"
    } else {
        transcryptIncomingString(this)
    }
}

internal fun String.capitalizeFirstCharsAndTranscrypt(): String {
    return transcryptCompletedEntityName(this)
        .split(SPLIT_BY_WHITESPACE)
        .map { cityNamed ->
            cityNamed
                .replaceFirstChar { firstChar ->
                    firstChar.uppercase()
                }
        }
        .joinToString(separator = SPLIT_BY_WHITESPACE) { everyCityName ->
            everyCityName
        }

}

internal fun transcryptIncomingString(incoming: String): String {
    var string = incoming
        .lowercase()
        .trim()

    fewSymbolsMapTranscryptions.forEach { MapEntry ->
        string = string.replace(MapEntry.value, MapEntry.key, ignoreCase = true)
    }
    singleSymbolMapTranscrypt.forEach { MapEntry ->
        string = string.replace(MapEntry.value, MapEntry.key, ignoreCase = true)
    }
    val transcrypted = string
    return transcrypted
}

internal fun transcryptCompletedEntityName(incoming: String): String {
    var string = incoming
        .lowercase()
        .trim()

    fewSymbolsMapTranscryptions.forEach { MapEntry ->
        string = string.replace(MapEntry.key, MapEntry.value, ignoreCase = true)
    }
    singleSymbolMapTranscrypt.forEach { MapEntry ->
        string = string.replace(MapEntry.key, MapEntry.value, ignoreCase = true)
    }
    val transcrypted = string
    return transcrypted
}
