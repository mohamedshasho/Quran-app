package com.example.quranapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.quranapp.models.Verse
import kotlin.random.Random

@Dao
interface VerseDao {



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVerse(verse: Verse)

    @Query("Select * from verse")
    fun getVerses(): LiveData<List<Verse>>

    @Query("Select * from verse where verse_key like :idVerse || '%'")
    fun getVerse(idVerse: String): LiveData<List<Verse>>

    @Query("Select * from verse where id between :start and :end")
    fun getJuz(start: Int, end: Int): LiveData<List<Verse>>

    @Query("select text from verse where id=:num")
    fun getRandomVerse(num:Int): LiveData<String>

    @Query("update verse set favorite=case (select favorite from verse where id=:id)when 1 then 0 when 0 then 1 end where id= :id")
    fun setFavorite(id: Int)

    @Query("select * from  verse where favorite=1")
    fun getFavorites(): LiveData<List<Verse>>

    @Query("select * from  verse where id=:id")
    fun getVerse(id:Int): LiveData<Verse>


    @Query("select id from verse order by id desc limit 1 ")
    fun checkIsEmpty():Int


}