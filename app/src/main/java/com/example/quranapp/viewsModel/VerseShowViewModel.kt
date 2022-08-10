package com.example.quranapp.viewsModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quranapp.repo.DbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VerseShowViewModel(private val db: DbRepository) : ViewModel() {

    fun getVerses(id: Int) =db.getVersesFromDatabase("$id:")

    fun getJuz(start: Int, end: Int)=db.getJuzFromDatabase(start, end)

    fun setFavorite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            db.setFavoriteVerse(id)
        }

    }



}