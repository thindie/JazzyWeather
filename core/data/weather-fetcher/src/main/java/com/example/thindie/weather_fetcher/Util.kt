package com.example.thindie.weather_fetcher

import android.util.Log
import com.example.thindie.domain.RatificationAble
import com.example.thindie.domain.entities.ForecastAble

fun Any?.WTF(foo: () -> String): Unit {
    if (this != null) {
        Log.d("SERVICE_TAG", "${this::class.java.simpleName} - Logging ${foo.invoke()} ")
    } else Log.d("SERVICE_TAG", "from func ${foo.invoke()} - Logging")

}

data class FetchPermission(private val allowed: Boolean) : RatificationAble {
    override fun isAllowed(): Boolean {
        return allowed
    }
}

data class FetchUnit(
    val place: String,
    val latitude: Float,
    val longitude: Float,
    val timezone: String,
    val concreteTime: String? = null,
) : ForecastAble {
    override fun getSight() = place

    override fun getSightLatitude() = latitude

    override fun getSightLongitude() = longitude

    override fun getTimeZone() = timezone

}


fun ForecastAble.toFetchUnit(concreteTime: String?) = FetchUnit(
    place = getSight(),
    latitude = getSightLatitude(),
    longitude = getSightLongitude(),
    timezone = getTimeZone(),
    concreteTime = concreteTime
)