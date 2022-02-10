package com.example.quranapp.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Verse(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("verse_key")
    @ColumnInfo(name = "verse_key")
    val verseKey: String,
    @SerializedName("text_uthmani")
    @NonNull
    val text: String,
    val favorite: Int = 0
)

//"verses": [
//{
//    "id": 1,
//    "verse_key": "1:1",
//    "text_uthmani": "بِسْمِ ٱللَّهِ ٱلرَّحْمَـٰنِ ٱلرَّحِيمِ"
//}
//]