package com.example.jazzyweather.domain.abstractions

import android.util.Log

sealed class Results<out T> {
    data class Success<T>(val data: T) : Results<T>()
    data class Error(val e: Exception) : Results<Nothing>()

    override fun toString(): String {

        return when (this) {
            is Success<T> -> {
                this.data!!::class.java.toString()
            }
            is Error -> {
                this.e.toString()
            }
        }
    }
}

fun <T, R> Results<T>.checkAndTransit(mapper: (T) -> R): Results<R> {
    return when (val t = this) {
        is Results.Success -> {
            Results.Success(mapper(t.data))
        }
        is Results.Error -> {
            Results.Error(t.e).apply {
                Log.d("SERVICE_TAG", this.e.toString())
            }
        }
    }
}

fun <T : Any?> T.encapsulateResult(): Results<T> {
    val t: T = this
    if (t is Results.Error) {
        Log.d("SERVICE_TAG", t.toString())
        return t
    }
    if (t as Any? == null) {
        return Results.Error(
            NullPointerException("Bad Results - null obj on encapsulation").apply {
                Log.d("SERVICE_TAG", this.toString())
            }
        )
    }
    if (t is Collection<*>) {
        if ((t as Collection<*>).isEmpty()) {
            return Results.Error(
                NullPointerException("Bad Results - empty Iterable<{$t}> here. data lost / or not received").apply {
                    Log.d("SERVICE_TAG", this.toString())
                }
            )
        }
    }



    return Results.Success(t)
}

fun <T> Results<T>.unpackResult(): T? {
    when (this) {
        is Results.Success -> {
            if (this.data == null) {
                Log.d("SERVICE_TAG", "null on unpackResult, $this")
                return null
            }
            if (this.data is Collection<*>) {
                return if ((this.data as Collection<*>).isEmpty()) {
                    Log.d("SERVICE_TAG", "empty Collection data, $this")
                    null
                } else this.data
            }
            if (this.data !is Collection<*>) {
                return this.data
            }
            return this.data
        }
        is Results.Error -> {
            Log.d("SERVICE_TAG", this.toString())
            return null
        }
    }

}

