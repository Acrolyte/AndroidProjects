package com.example.retrofit_practice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_practice.Model.Post
import com.example.retrofit_practice.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse : MutableLiveData<Response<Post>> = MutableLiveData()
    val myCustompost : MutableLiveData<Response<List<Post>>> = MutableLiveData()

    fun getPost(){
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }

    fun getCustomPost(userId : Int){
        viewModelScope.launch {
            val response = repository.getCustomPost(userId)
            myCustompost.value = response
        }
    }
}