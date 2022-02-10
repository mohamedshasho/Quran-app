package com.example.quranapp.services


import com.example.quranapp.utils.Constants

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstant {
    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)


        suspend fun getVerses(): Call<ListVerse> {
            val response = retrofit.getVerses()
            return response
        }

        suspend fun getSuras(): Call<ListSura> {
            val response = retrofit.getSuras()
            return response;
        }


        suspend fun getAllJuz(): Call<ListJuz> {
            val response = retrofit.getAllJuz()
            return response
        }
    }


}