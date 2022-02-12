package com.example.quranapp.viewsModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quranapp.models.Juz
import com.example.quranapp.models.Sura
import com.example.quranapp.models.Verse
import com.example.quranapp.repo.DbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Random


class MainViewModel(private val dbRepository: DbRepository) : ViewModel() {

    companion object {
        private const val SIZE_OF_VERSES = 6236;
    }

    val verse = dbRepository.getRandomVerse(Random().nextInt(SIZE_OF_VERSES))

    val error = MutableLiveData<String>()
    val progressBar = MutableLiveData<Boolean>()


    fun getSurasFromDb() = dbRepository.getSurasFromDatabase()
    fun getJuzsFromDb(): LiveData<List<Juz>> = dbRepository.getAllJuzFromDatabase()


    /*
     create fun to load data from api to fill room
     if check room null then fill the room
    when there is error call second fun and post value message of error
    else post value progress bar
    */
    fun loadDataFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            dbRepository.fillDataFromNetwork({
                progressBar.postValue(it)
            }, {
                error.postValue(it)
            })
        }
    }

    private suspend fun insertSura(sura: Sura) {
        dbRepository.insertSura(sura)
    }

    private suspend fun insertJuz(juz: Juz) {
        dbRepository.insertJuz(juz)
    }

    private suspend fun insertVerse(verse: Verse) {
        dbRepository.insertVerse(verse)
    }


}
