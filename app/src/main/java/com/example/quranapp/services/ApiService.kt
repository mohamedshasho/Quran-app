package com.example.quranapp.services


import com.example.quranapp.utils.Constants
import com.example.quranapp.models.Juz
import com.example.quranapp.models.Sura
import com.example.quranapp.models.Verse

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {

    @GET(Constants.QURAN_URL)
    fun getVerses(): Call<ListVerse>

    @GET(Constants.CHAPTERS_URL)
    fun getSuras(): Call<ListSura>

    @GET(Constants.URL_JUZs)
    fun getAllJuz(): Call<ListJuz>

}

class ListSura(@SerializedName("chapters") val suras: List<Sura>)
class ListVerse(val verses: List<Verse>)
class ListJuz(val juzs: List<Juz>)