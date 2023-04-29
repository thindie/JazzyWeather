package com.example.thindie.core.network.util


import com.google.gson.Gson
import retrofit2.Response


suspend fun <R : Response<String>> serviceResponseThrowOrResult(foo: suspend () -> R): String {
    return if (foo().isSuccessful) checkNotNull(foo().body())
    else error(
        " where: com.example.thindie.core.network.util.serviceResponseThrowOrResult. why: " +
                "response is unSuccessful"
    )
}

inline fun <reified T> String.parseJsonTo(): T {
    return Gson().fromJson(this, T::class.java)
}



