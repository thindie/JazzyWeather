package com.example.jazzyweather.domain

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
            Results.Error(t.e)
        }
    }
}

fun <T : Any?> T.encapsulateResult(): Results<T> {
    val t: T = this
    if (t as Any? == null) {
        return Results.Error(
            NullPointerException("Bad Results - null obj on encapsulation")
        )
    }
    if (t is Collection<*>) {
        if ((t as Collection<*>).isEmpty()) {
            return Results.Error(
                NullPointerException("Bad Results - empty Iterable<{$t}> here. data lost / or not received")
            )
        }
    }


    return Results.Success(t)
}

fun <T> Results<T>.unpackResult(): T? {
    when (this) {
        is Results.Success -> {
            if (this.data == null) {
                return null
            }
            if (this.data is Collection<*>) {
                return if ((this.data as Collection<*>).isEmpty()) {
                    null
                } else this.data
            }
            if (this.data !is Collection<*>) {
                return this.data
            }
            return this.data
        }
        is Results.Error -> {
            return null
        }
    }

}
