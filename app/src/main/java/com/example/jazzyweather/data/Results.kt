package com.example.jazzyweather.data

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