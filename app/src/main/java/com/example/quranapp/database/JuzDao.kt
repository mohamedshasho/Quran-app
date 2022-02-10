package com.example.quranapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quranapp.models.Juz


@Dao
interface JuzDao {
    @Insert
   suspend fun insertJuz(juz: Juz)

    @Query("Select * from juz_table")
    fun getAllJuz():LiveData<List<Juz>>

//    @Query("Select id,text from juz_table,Verse where Verse.id between first_verse_id and last_verse_id")
//    fun getJuz(): List<Verse>
}