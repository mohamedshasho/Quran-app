package com.example.quranapp.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName

@Entity(tableName = "sura_table")
data class Sura(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("name_simple")
    var name: String?,
    @SerializedName("verses_count")
    @ColumnInfo(name = "verses_count")
    var versesCount: Int?,
    @SerializedName("name_arabic")
    @ColumnInfo(name = "name_arabic")
    var nameArabic: String?,
    @SerializedName("translated_name")
    @ColumnInfo(name = "trans_name")
    var translatedName: TranslatedName?,

    ) {

    constructor(
        name: String,
        versesCount: Int,
        nameArabic: String,
        transName: TranslatedName
    ) : this(
        0,
        name,
        versesCount,
        nameArabic,
        transName
    )
}



//{
//    "chapters": [
//    {
//        "id": 1,
//        "revelation_place": "makkah",
//        "revelation_order": 5,
//        "bismillah_pre": false,
//        "name_simple": "Al-Fatihah",
//        "name_complex": "Al-Fātiĥah",
//        "name_arabic": "الفاتحة",
//        "verses_count": 7,
//        "pages": [
//        1,
//        1
//        ],
//        "translated_name": {
//        "language_name": "english",
//        "name": "The Opener"
//    }
//    }
//    ]
//}