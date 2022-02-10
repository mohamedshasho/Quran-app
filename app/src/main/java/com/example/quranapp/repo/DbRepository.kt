package com.example.quranapp.repo


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.quranapp.database.MyRoomDatabase
import com.example.quranapp.models.Juz
import com.example.quranapp.models.Sura
import com.example.quranapp.models.Verse
import com.example.quranapp.services.RetrofitInstant
import retrofit2.awaitResponse

class DbRepository(private val db: MyRoomDatabase) {

    companion object{
        private var instant: DbRepository? = null

        fun getInstant(db: MyRoomDatabase): DbRepository {
            if (instant == null) {
              instant= DbRepository(db)
            }
            return instant!!
        }
    }

     fun getSurasFromDatabase() = db.SuraDao().getSuras()
     fun getVersesFromDatabase(idVerse: String) = db.VerseDao().getVerse(idVerse)
     fun getJuzFromDatabase(start: Int, end: Int) = db.VerseDao().getJuz(start, end)
     fun getAllJuzFromDatabase() = db.JuzDao().getAllJuz()
     fun getRandomVerse(num: Int):LiveData<String> = db.VerseDao().getRandomVerse(num)

    suspend fun insertJuz(juz: Juz) = db.JuzDao().insertJuz(juz)
    suspend fun insertSura(sura: Sura) = db.SuraDao().insertSura(sura)
    suspend fun insertVerse(verse: Verse) = db.VerseDao().insertVerse(verse)

    suspend fun setFavoriteVerse(id: Int) = db.VerseDao().setFavorite(id)
    suspend fun getAllFavoriteVerse(): LiveData<List<Verse>> = db.VerseDao().getFavorites()
    suspend fun getVerseFromDatabase(id: Int): LiveData<Verse> = db.VerseDao().getVerse(id)


    suspend fun getSurasFromNetwork(
        onSuccess: (suras: List<Sura>)-> Unit,
        onError: (error: String) -> Unit
    ) {
        val data = RetrofitInstant.getSuras().awaitResponse()
        if (data.isSuccessful) {
            onSuccess(data.body()!!.suras)
        } else {
            onError(data.message())
        }
    }

    suspend fun getAllVersesFromNetwork(
        onSuccess: (verses: List<Verse>) -> Unit,
        onError: (error: String) -> Unit
    ) {
        val data = RetrofitInstant.getVerses().awaitResponse()
        if (data.isSuccessful) {
            for (verse in data.body()!!.verses) {
                insertVerse(verse)
            }
            onSuccess(data.body()!!.verses)
        } else {
            onError(data.message())
        }
    }

    suspend fun getAllJuzFromNetwork(
        onSuccess: (Juzs: List<Juz>) -> Unit,
        onError: (error: String) -> Unit
    ) {
        val data = RetrofitInstant.getAllJuz().awaitResponse()
        if (data.isSuccessful) {
            onSuccess(data.body()!!.juzs)
        } else {
            onError(data.message())
        }

    }

}