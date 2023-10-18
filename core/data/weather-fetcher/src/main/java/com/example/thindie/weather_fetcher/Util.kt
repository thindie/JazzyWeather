package com.example.thindie.weather_fetcher

import android.util.Log
import com.example.thindie.domain.RatificationAble

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
