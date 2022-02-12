package com.example.quranapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quranapp.models.Sura


@Dao
interface SuraDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend  fun insertSura(sura: Sura)
    //fun insertSura(vararg sura: Sura)
    //vararg likes in java multi params like Type ...var

    @Query("Select * from sura_table")
    fun getSuras(): LiveData<List<Sura>>

    @Query("select id from sura_table order by id desc limit 1")
    fun  checkIsEmpty(): Int
}