package com.example.quranapp.viewsModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quranapp.repo.DbRepository

class VerseShowViewModelFactory(private val repository: DbRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VerseShowViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VerseShowViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}