package com.subhranil.envelope.db

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromString(value: String): List<String> = value.split("\n")

    @TypeConverter
    fun fromList(list: List<String>): String = list.joinToString("\n")
}