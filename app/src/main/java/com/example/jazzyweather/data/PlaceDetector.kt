package com.example.jazzyweather.data


import android.app.Application
import com.example.jazzyweather.data.local.PossibilityFromJson
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.domain.abstractions.Results
import com.example.jazzyweather.domain.abstractions.encapsulateResult
import com.example.thindie.wantmoex.R
import com.google.gson.Gson
import com.google.gson.JsonArray
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject


class PlaceDetector @Inject constructor(
    private val application: Application,
) {

    fun produce(toFind: String): Flow<Results<Possibility>> {
        if (toFind.matches(
                "^\\d{2,3}.\\d{2,}\\s{1,}\\d{2,3}.\\d{2,}"
                    .toRegex()
            )
        ) {
            return flow { emit(toFind.parseAsCoordinates()) }
        }
        val tag = toFind.legitTag()
        val stream = application.resources.openRawResource(R.raw.ru)
        val array = Gson()
            .fromJson(
                BufferedReader(InputStreamReader(stream)), JsonArray::class.java
            )
        return flow {
            array.iterator().forEach {
                val city = it.asJsonObject.get("city").toString()
                if (city.contains(tag, true)
                    || city == tag
                    || tag.contains(city, true)
                ) {
                    val toEmit = Gson()
                        .fromJson(it, PossibilityFromJson::class.java)
                        .map()
                    emit(toEmit.encapsulateResult())
                }
            }
        }
    }
}

private fun String.parseAsCoordinates(): Results<Possibility> {
    this.split("\\s+".toRegex()).apply {
        return Possibility(
            place = "Мое место",
            latitude = this[0].toFloat(),
            longitude = this[1].toFloat(),
            timeZone = FAKE_TIME_ZONE,
            admin_name = ""
        ).encapsulateResult()
    }
}

operator fun <T> JSONArray.iterator(): Iterator<T> =
    (0 until this.length()).asSequence().map { this.get(it) as T }.iterator()


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
    "Saint Petersburg" to "Санкт-Петербург",
    "Europe" to "Европа",
    "Sovetsk" to "Советск",
    "Astrakhan" to "Астрахань",
    "Kazan" to "Казань",
    "nyye" to "ные",
    "Tver" to "Тверь",
    "airport" to "Аэропорт",
    "Arkhangelsk" to "Архангельск",
    "house" to "хаус",
    "cottage" to "коттэдж",
    "tower" to "тауэр",
    "building" to "билдинг",
    "station" to "станция",
    "Podolsk" to "Подольск",
    "Asia" to "Азия",
    "ishche" to "ище",
    "niye" to "ние",
    "Yosh" to "Йош",
    "Shchel" to "Щёл",
    "ntsyn" to "нцын",
    "Nalchik" to "Нальчик",
    "Engels" to "Энгельс",
    "atsk" to "атск",
    "etsk" to "ецк",
    "ryan" to "рян",
    "zhniy" to "жний",
    "nyy" to "ный",
    "chyor" to "чёр",
    "cher" to "чер",
    "ol’" to "оль",
    "al’" to "аль",
    "el’" to "ель",
    "il’" to "иль",
    "yany" to "яны",
    "ayo" to "айо",
    "еschе" to "еще",
    "nts" to "нц",
    "siy" to "сий",
    "yu" to "ю",
    "ye" to "е",
    "ya" to "я",
    "tsk" to "цк",
    "ets" to "ец",
    "iy" to "ий",
    " ts" to " Це",
    "ry" to "ры",
    "vy" to "вы",
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

private const val FAKE_TIME_ZONE = "Europe/Moscow"

private fun String.legitTag(): String {

    var tag = try {
        this.lowercase().trim()
    } catch (e: Exception) {
        return "Bad Data"
    }
    if (this == ""
        || "Moskva".contains(this, true)
        || "Москва".contains(this, true)
    ) {
        tag = "Moscow"; return tag
    } else if (
        "Peterburg".contains(this, true)
        || "SanktPeterburg".contains(this, true)
        || "Piter".contains(this, true)
        || "Питер Петербург Санкт".contains(this, true)
    ) {
        tag = "Saint"; return tag
    }

    doublePhono.forEach {
        tag = tag.replace(it.value, it.key, true)
    }
    transMap.forEach {
        tag = tag.replace(it.value, it.key, ignoreCase = true)
    }
    return tag
}

fun String.transmutate(): String {
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
