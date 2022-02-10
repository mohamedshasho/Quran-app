package com.example.quranapp.viewsModel


import androidx.lifecycle.LiveData
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

    fun getSurasFromDb() = dbRepository.getSurasFromDatabase()
    fun getJuzsFromDb(): LiveData<List<Juz>> = dbRepository.getAllJuzFromDatabase()






    fun loadDataFromNetwork() {
        viewModelScope.launch(Dispatchers.IO) {
//            dbRepository.getSurasFromNetwork({ suras ->
//                viewModelScope.launch(Dispatchers.IO) {
//                    for (sura in suras) {
//                        insertSura(sura)
//                    }
//                }
//            }, {
//                //todo get error
//            })
//            dbRepository.getAllJuzFromNetwork({
//                viewModelScope.launch(Dispatchers.IO) {
//                    for (juz in it) {
//                        insertJuz(juz)
//                    }
//                }
//            }, {
//                //todo get error
//            })
            dbRepository.getAllVersesFromNetwork({
                viewModelScope.launch(Dispatchers.IO) {
                    for (verse in it) {
                        insertVerse(verse)
                    }
                }
            }, {
                //todo get error
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

//    fun getSuras(onSuccess: (suras: LiveData<List<Sura>>) -> Unit) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val data = dbRepository.getSurasFromDatabase()
//            withContext(Dispatchers.Main) {
//                onSuccess(data)
//            }
//        }
//    }
//
//    fun getAllJuz(onSuccess: (juzs: LiveData<List<Juz>>) -> Unit) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val data = dbRepository.getAllJuzFromDatabase()
//            withContext(Dispatchers.Main) {
//                onSuccess(data)
//            }
//        }
//    }


}
