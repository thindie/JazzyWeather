package com.example.jazzyweather.data

sealed class Results<out T>{
    data class Success<T>(val data: T): Results<T>()
    data class Error<T>(val e : Exception): Results<Nothing>()
}