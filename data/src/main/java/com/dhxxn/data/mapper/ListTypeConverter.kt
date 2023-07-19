package com.dhxxn.data.mapper

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.dhxxn.domain.common.model.Like
import com.google.gson.Gson

@ProvidedTypeConverter
class ListTypeConverter(private val gson: Gson) {

    @TypeConverter
    fun listToJson(value: List<Like>): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Like> {
        return gson.fromJson(value, Array<Like>::class.java).toList()
    }
}