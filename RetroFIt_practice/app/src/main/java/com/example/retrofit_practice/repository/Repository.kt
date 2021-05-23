package com.example.retrofit_practice.repository

import com.example.retrofit_practice.Model.Post
import com.example.retrofit_practice.api.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun getPost(): Response<Post>{
        return RetrofitInstance.api.getPost()
    }
    suspend fun getCustomPost(userId : Int): Response<List<Post>>{
        return RetrofitInstance.api.getCustomPost(userId)
    }
}