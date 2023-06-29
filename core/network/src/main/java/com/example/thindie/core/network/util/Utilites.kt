package com.example.thindie.core.network.util


import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Response


suspend fun <R : Response<JsonObject>> serviceResponseThrowOrResult(foo: suspend () -> R): String {
    return if (foo().isSuccessful)  foo().body().toString()
    else error(
        " where: com.example.thindie.core.network.util.serviceResponseThrowOrResult. why: " +
                "response is unSuccessful"
    )
}

inline fun <reified T> String.parseJsonTo(): T {
    return Gson().fromJson(this, T::class.java)
}



