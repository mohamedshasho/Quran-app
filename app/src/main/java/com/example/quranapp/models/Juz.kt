package com.example.quranapp.models

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "juz_table",
//    indices = { @Index(value = {"first_verse_id"}, unique = true)}
//    foreignKeys = arrayOf(
//        ForeignKey(
//            entity = Verse::class,
//            parentColumns = arrayOf("id"),
//            childColumns = arrayOf("first_verse_id", "last_verse_id")
//        )
//    )
)
data class Juz(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("first_verse_id")
    @ColumnInfo(name = "first_verse_id")
    var firstVerseId: Int,
    @SerializedName("last_verse_id")
    @ColumnInfo(name = "last_verse_id")
    var lastVerseId: Int,
    @SerializedName("verses_count")
    @ColumnInfo(name = "verses_count")
    var versesCount: Int
)


//{
//    "id": 1,
//    "juz_number": 1,
//    "verse_mapping": {
//    "1": "1-7",
//    "2": "1-141"
//},
//    "first_verse_id": 1,
//    "last_verse_id": 148,
//    "verses_count": 148
//},