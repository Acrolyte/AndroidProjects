package com.example.retrofit_practice.api

import com.example.retrofit_practice.Model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleApi {

    @GET("posts/1")
    suspend fun getPost(): Response<Post>

    @GET("posts")
    suspend fun getCustomPost(
        @Query("userId") userId : Int
    ): Response<List<Post>>
}