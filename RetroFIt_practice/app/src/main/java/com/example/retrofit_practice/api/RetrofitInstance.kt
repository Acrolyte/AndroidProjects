package com.example.retrofit_practice.api

import com.example.retrofit_practice.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api : SimpleApi by lazy {
        retrofit.create(SimpleApi :: class.java)
    }
}