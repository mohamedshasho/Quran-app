package com.example.quranapp.services


import com.example.quranapp.utils.Constants

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstant {
    companion object {
       private val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)


        fun getVerses(): Call<ListVerse> {
           return retrofit.getVerses()
        }

         fun getSuras(): Call<ListSura> {
            return retrofit.getSuras()

        }


        fun getAllJuz(): Call<ListJuz> {
            return retrofit.getAllJuz()
        }
    }


}