package com.example.quranapp.database

import androidx.room.TypeConverter
import com.example.quranapp.models.TranslatedName
import com.google.gson.Gson

class TypeGsonConverter {

    @TypeConverter
    fun fromTranslatedNameToString(translatedName: TranslatedName): String {
        return Gson().toJson(translatedName)
    }

    @TypeConverter
    fun fromStringToTranslatedName(stringTranslatedName: String): TranslatedName {
        return Gson().fromJson(stringTranslatedName, TranslatedName::class.java)
    }
}