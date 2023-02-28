package com.example.jazzyweather.data

import android.app.Application
import com.example.jazzyweather.di.DispatchersModule
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.domain.Results
import com.example.jazzyweather.domain.encapsulateResult
import com.example.thindie.wantmoex.R
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

private const val LOCATION_HOLDER = 1
private const val BUFFER = 8192
private const val READ_PROPERLY = 400L

class PlaceDetector @Inject constructor(
    @DispatchersModule.IODispatcher private val IO: CoroutineDispatcher,
    private val scope: CoroutineScope,
    private val application: Application,
) {
     private val lock: Any = Any()

     fun produce(toFind: String): Flow<Results<List<Possibility>>> {
        return flow {
            val possibleLocationsList = synchronized(lock) {
                mutableListOf<Possibility>()
            }
            scope.launch(IO) {
                val tag = toFind.lowercase().trim()


                val inputStream1 = application.resources.openRawResource(R.raw.coord1)
                val inputStream2 = application.resources.openRawResource(R.raw.coord2)
                val inputStream3 = application.resources.openRawResource(R.raw.coord3)
                val inputStream4 = application.resources.openRawResource(R.raw.coord1a)
                val inputStream5 = application.resources.openRawResource(R.raw.coord2a)
                val inputStream6 = application.resources.openRawResource(R.raw.coord3a)
                val streams = listOf(
                    inputStream1,
                    inputStream2,
                    inputStream3,
                    inputStream4,
                    inputStream5,
                    inputStream6
                )


                streams.forEach { inputStream ->
                    scope.launch(IO) {
                        val reader = BufferedReader(InputStreamReader(inputStream), BUFFER)
                        while (reader.ready() && possibleLocationsList.size < 10) {
                            val line = reader.readLine()
                            if (line.lowercase().contains(tag)) {
                                line.getCoordinates()?.let { possibleLocationsList.add(it) }
                            }
                        }
                        inputStream.close()
                        reader.close()
                        this.cancel()
                    }

                }

                this.cancel()
            }
            delay(READ_PROPERLY)
            emit(possibleLocationsList.encapsulateResult())
        }

    }

}

private fun String.getCoordinates(): Possibility? {
    var longitude: Float? = null
    var latitude: Float? = null
    val splitted = this.split("\t")

    splitted.forEach {
        if (it.matches("\\d{2}[.]\\d*".toRegex())) {
            if (latitude == null) {
                latitude = it.toFloat()
            } else longitude = it.toFloat()
        }

    }
    return if (latitude == null || longitude == null) {
        return null
    } else {
        Possibility(
            place = splitted[LOCATION_HOLDER],
            latitude = latitude!!,
            longitude = longitude!!
        )
    }
}


