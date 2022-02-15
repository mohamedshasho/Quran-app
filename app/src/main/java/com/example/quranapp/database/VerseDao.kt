package com.example.quranapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.quranapp.models.Verse
import com.example.quranapp.models.VerseSura
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

    /*
    VerseSura class should have variables names as column name
     or (using as name equals name in VerseSura)
     */
    @Query("select sura_table.id , verse.text as textVerse ,sura_table.name as nameSura from  verse INNER JOIN  sura_table on verse.verse_key like sura_table.id || ':%'  where verse.id=:num")
    fun getRandomVerse(num:Int): LiveData<VerseSura>

    @Query("update verse set favorite=case (select favorite from verse where id=:id)when 1 then 0 when 0 then 1 end where id= :id")
    fun setFavorite(id: Int)

    @Query("select * from  verse where id=:id")
    fun getVerse(id:Int): LiveData<Verse>


    @Query("select id from verse order by id desc limit 1 ")
    fun checkIsEmpty():Int

    @Query("select * from verse where favorite=1")
    fun getFavoriteVerse():LiveData<List<Verse>>


}