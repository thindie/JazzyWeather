package com.example.jazzyweather.data

import android.app.Application
import android.util.Log
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
private const val READ_PROPERLY = 700L

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
                val tag = toFind.legitTag()


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
                        while (reader.ready() && possibleLocationsList.size < 15) {
                            val line = reader.readLine()
                            if (line.lowercase().subSequence(0,line.length.div(2)).contains(tag)) {
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
            Log.d("SERVICE_TAG", "$possibleLocationsList")
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
            place = splitted[LOCATION_HOLDER].transmutate(),
            latitude = latitude!!,
            longitude = longitude!!,
            timeZone = splitted[splitted.size - 2].transmutate()
        )
    }
}

private val transMap = mapOf(
    "a" to "а",
    "b" to "б",
    "v" to "в",
    "g" to "г",
    "d" to "д",
    "e" to "е",
    "z" to "з",
    "i" to "и",
    "j" to "й",
    "k" to "к",
    "l" to "л",
    "m" to "м",
    "n" to "н",
    "o" to "о",
    "p" to "п",
    "r" to "р",
    "s" to "с",
    "t" to "т",
    "u" to "у",
    "f" to "ф",
    "c" to "ц",
    "y" to "й",
    "’" to "ь",
    "’’" to "ъ",

)

private val doublePhono = mapOf(
    "/" to " ",
    "Moscow" to "Москва",
    "Europe" to "Европа",
    "Asia" to "Азия",
    "niye" to "ние",
    " ts" to " Це",
    "ishche" to "ище",
    "nyy" to "ный",
    "yany" to "яны",
    "ntsyn" to "нцын",
    "ayo" to "айо",
    "nts" to "нц",
    "yu" to "ю",
    "ye" to "е",
    "ya" to "я",
    "atsk" to "атск",
    "tsk" to "цк",
    "ets" to "ец",
    "iy" to "ий",
    "ry" to "ры",
    "vy" to "ы",
    "zy" to "зы",
    "ty" to "ты",
    "khy" to "хи",
    "hy" to "хы",
    "ly" to "лы",
    "gy" to "гы",
    "dy" to "ды",
    "fy" to "фы",
    "my" to "мы",
    "by" to "бы",
    "cy" to "цы",
    "kh" to "х",
    "ch" to "ч", //
    "sh" to "ш", //
    "sh'" to "щ", //
    "yo" to "ё",
    "zh" to "ж",

   //
)
private fun String.legitTag(): String{
    var tag = try {
        this.lowercase().trim()
    }catch (e: Exception){ return "Bad Data"}
    doublePhono.forEach {
        tag = tag.replace(it.value, it.key, true)
    }
    transMap.forEach {
        tag = tag.replace(it.value, it.key, ignoreCase = true)
    }
    tag = if(tag.length > 15) tag.subSequence(0,15).toString() else tag
    return tag
}

private fun String.transmutate(): String{
    var string = this.lowercase()
    doublePhono.forEach {
        string = string.replace(it.key, it.value, true)
    }
    transMap.forEach {
        string = string.replace(it.key, it.value, true)
    }
    string = string.split(" ").map {
        it.replaceFirstChar {
            it.uppercase()
        }
    }.joinToString(separator = " ") {
        it
    }

    return string
}

