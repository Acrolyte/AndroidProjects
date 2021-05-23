package com.example.retrofit_practice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit_practice.databinding.ActivityMainBinding
import com.example.retrofit_practice.repository.Repository


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel :: class.java)


        viewModel.getCustomPost(3)

        viewModel.myCustompost.observe(this, Observer { response ->
            if (response.isSuccessful){
                binding.tvResult.text = response.body().toString()
            }else{
                Log.d("Response",response.errorBody().toString())
                binding.tvResult.text = response.code().toString()
            }
        })
    }
}