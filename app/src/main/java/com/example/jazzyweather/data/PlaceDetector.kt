package com.example.jazzyweather.data


import android.app.Application
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.domain.abstractions.Results
import com.example.jazzyweather.domain.abstractions.encapsulateResult
import com.example.thindie.wantmoex.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset
import javax.inject.Inject

private const val LOCATION_HOLDER = 1


class PlaceDetector @Inject constructor(
    private val application: Application,
) {

     fun produce(toFind: String): Flow<Results<Possibility>> {
        val tag = toFind.legitTag()
        val stream = application.resources.openRawResource(R.raw.coord1)
        val stream1 = application.resources.openRawResource(R.raw.coord1a)
        val stream2 = application.resources.openRawResource(R.raw.coord2)
        val stream3 = application.resources.openRawResource(R.raw.coord2a)
        val stream4 = application.resources.openRawResource(R.raw.coord3)
        val stream5 = application.resources.openRawResource(R.raw.coord3a)
        val streamList = listOf(stream1, stream, stream2, stream3, stream3, stream5, stream4)
        val list = synchronized(Any()) { mutableListOf<Possibility>() }
        streamList.forEach {
            val reader = BufferedReader(InputStreamReader(it))
            if (list.size > MAX_LIST_SIZE) {
                reader.close(); return@forEach
            }
            reader
                .lineSequence()
                .filter {
                    it.subSequence(0, it.length.div(2)).contains(tag)
                }.forEach {
                    it.getCoordinates()?.let { it1 -> list.add(it1) }
                }
        }
        return list.asFlow().map { it.encapsulateResult() }
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
    "airport" to "Аэропорт",
    "house" to "хаус",
    "cottage" to "коттэдж",
    "tower" to "тауэр",
    "building" to "билдинг",
    "station" to "станция",
    "Asia" to "Азия",
    "ishche" to "ище",
    "niye" to "ние",
    "ntsyn" to "нцын",
    "atsk" to "атск",
    "nyy" to "ный",
    "ol’" to "оль",
    "al’" to "аль",
    "el’" to "ель",
    "il’" to "иль",
    "yany" to "яны",
    "ayo" to "айо",
    "nts" to "нц",
    "yu" to "ю",
    "ye" to "е",
    "ya" to "я",
    "tsk" to "цк",
    "ets" to "ец",
    "iy" to "ий",
    " ts" to " Це",
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
private const val MAX_LIST_SIZE = 40