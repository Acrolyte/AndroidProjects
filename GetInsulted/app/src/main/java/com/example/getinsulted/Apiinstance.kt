package com.example.getinsulted

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object Apiinstance {
    fun clientdata(): badApi {
        val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://evilinsult.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
        val api: badApi by lazy {
            retrofit.create(badApi::class.java)
        }
        return api
    }
}

interface badApi {

    @GET("/generate_insult.php")
    suspend fun getPost(
        @Query("lang") lang: String,
        @Query("type") type: String
    ): Response<baddata>

}