package com.example.thindie.database.typeconverters

import androidx.room.TypeConverter

class StringListTypeConverter {
    @TypeConverter
    fun fromBase(converted: String): List<String> {
        return converted.split(separator)
    }

    @TypeConverter
    fun toBase(list: List<String>): String {
        return list.joinToString(separator)
    }
}

class DoubleListTypeConverter {
    @TypeConverter
    fun fromBase(converted: String): List<Double> {
        return converted.split(separator).map {
            try {
                it.toDouble()
            } catch (_: NumberFormatException) {
                0.0
            }
        }
    }


    @TypeConverter
    fun toBase(list: List<Double>): String {
        return list.joinToString(separator)
    }
}


class IntListTypeConverter {
    @TypeConverter
    fun fromBase(converted: String): List<Int> {
        return converted.split(separator).map {
            try {
                it.toInt()
            } catch (_: NumberFormatException) {
                0
            }
        }
    }

    @TypeConverter
    fun toBase(list: List<Int>): String {
        return list.joinToString(separator)
    }
}

private const val separator = "#####"

