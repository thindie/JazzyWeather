package com.example.jazzyweather.data

import android.app.Application
import com.example.jazzyweather.di.DispatchersModule
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.domain.abstractions.Results
import com.example.jazzyweather.domain.abstractions.encapsulateResult

import com.example.thindie.wantmoex.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

private const val LOCATION_HOLDER = 1
private const val BUFFER = 8192


class PlaceDetector @Inject constructor(
    @DispatchersModule.IODispatcher private val IO: CoroutineDispatcher,
    private val application: Application,
) {

    suspend fun produce(toFind: String): Flow<Results<List<Possibility>>> {
        val tag = toFind.legitTag()
        if (tag == BAD_TAG || tag == EMPTY_TAG) return emptyFlow()
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
        val lock = Any()
        val resultList = synchronized(lock) { mutableListOf<Possibility>() }

        streams.forEach { inputStream ->
            val list = withContext(IO) {
                val bufferList = mutableListOf<Possibility>()
                val reader = BufferedReader(InputStreamReader(inputStream), BUFFER)
                while (withContext(IO) {
                        reader.ready()
                    }) {
                    if (bufferList.size > 3) {
                        withContext(Dispatchers.IO) {
                            inputStream.close()
                            reader.close()
                        }
                        return@withContext bufferList
                    }
                    val line =
                        withContext(IO) {
                            reader.readLine()
                        }
                    if (line.lowercase().subSequence(0, line.length.div(2))
                            .contains(tag)
                    ) {
                        line.getCoordinates()?.let { bufferList.add(it) }
                    }

                }
                bufferList
            }
            resultList.addAll(list)
        }

        return flow { emit(resultList.encapsulateResult()) }
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
                timeZone = splitted[splitted.size - 2],
                adaptedTimeZone = splitted[splitted.size - 2].transmutate(),
            )
        }
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

private fun String.legitTag(): String {
    var tag = try {
        this.lowercase().trim()
    } catch (e: Exception) {
        return "Bad Data"
    }
    doublePhono.forEach {
        tag = tag.replace(it.value, it.key, true)
    }
    transMap.forEach {
        tag = tag.replace(it.value, it.key, ignoreCase = true)
    }
    tag = if (tag.length > 15) tag.subSequence(0, 15).toString() else tag
    return tag
}

private fun String.transmutate(): String {
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


private const val BAD_TAG = "Bad Data"
private const val EMPTY_TAG = ""