package com.example.quranapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quranapp.models.Juz


@Dao
interface JuzDao {
    /*
    onConflict = OnConflictStrategy.IGNORE
    @todo Attention
    when user exit app before fill all records that was checking in checkIsEmpty
    then reCall api to fill all records and ignore the same records
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insertJuz(juz: Juz)

    @Query("Select * from juz_table")
    fun getAllJuz():LiveData<List<Juz>>

    @Query("Select id from juz_table order by id desc limit 1")
    fun checkIsEmpty():Int

//    @Query("Select id,text from juz_table,Verse where Verse.id between first_verse_id and last_verse_id")
//    fun getJuz(): List<Verse>
}