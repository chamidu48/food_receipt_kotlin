package com.example.foodapp.utils

import androidx.room.TypeConverter

class StringArrayConverter {
    @TypeConverter
    fun fromString(value: String?): Array<String>? {
        return value?.split(",")?.toTypedArray()
    }

    @TypeConverter
    fun toString(value: Array<String>?): String? {
        return value?.joinToString(",")
    }
}
