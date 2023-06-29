package com.example.thindie.presentation.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

//2023-05-12T21:33
class TimeParser private constructor(private val iso8106: String) {

    private val date
        get() = run {
            try {
                val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.UK)
                sdf.parse(iso8106)
            } catch (e: UnknownFormatConversionException) {
                Log.e("SERVICE_TAG_TIME_PARSE", iso8106)
                ""
            }
        }

    val day
        get() = run {
            try {
                val sdf: SimpleDateFormat = SimpleDateFormat("dd", Locale.UK)
                date?.let { sdf.format(it) }.toString()
            } catch (e: UnknownFormatConversionException) {
                Log.e("SERVICE_TAG_TIME_PARSE", iso8106)
                ""
            }
        }

    val time
        get() = run {
            try {
                val sdf: SimpleDateFormat = SimpleDateFormat("HH:mm", Locale.UK)
                date?.let { sdf.format(it) }.toString()
            } catch (e: UnknownFormatConversionException) {
                Log.e("SERVICE_TAG_TIME_PARSE", iso8106)
                ""
            }

        }


    companion object {
        operator fun invoke(iso8106: String) = TimeParser(iso8106)
    }
}




