package com.example.jazzyweather.data

import android.app.Application
import com.example.jazzyweather.di.DispatchersModule
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.domain.Results
import com.example.thindie.wantmoex.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

private const val LOCATION_HOLDER = 1
class PlaceDetector @Inject constructor(
    @DispatchersModule.IODispatcher private val IO: CoroutineDispatcher,
    private val application: Application,
) {
    suspend fun produce(toFind: String) =
        withContext(IO) {
            val inputStream = application.resources.openRawResource(R.raw.latit_longit)
            var result: Possibility? = null
            BufferedReader(InputStreamReader(inputStream)).readLines().filter {
                it.lowercase().contains(toFind.lowercase().trim())
            }.map {
                result = it.getCoordinates()
            }

            return@withContext if (result == null) Results.Error(Exception(MESSAGE)) else Results.Success(
                result
            )
        }

    companion object {
        private const val MESSAGE = "cannot find coordinates by tag"

    }
}


private fun String.getCoordinates(): Possibility? {
    var longitude: Float? = null
    var latitude: Float? = null
    val splitted = this.split("\t")

    splitted.forEach {
        if (it.matches("\\d{2}[.]\\d*".toRegex())) {
            if (longitude == null) {
                longitude = it.toFloat()
            } else latitude = it.toFloat()
        }

    }
    return if (latitude == null) {
        return null
    } else {
        Possibility(
            place = splitted[LOCATION_HOLDER],
            latitude = latitude!!,
            longitude = longitude!!
        )
    }
 }


