package com.example.getinsulted

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.getinsulted.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var apicall = Apiinstance.clientdata()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var s = "Wait for some time..."
        binding.badbutton.setOnClickListener {
            binding.badbutton.visibility = Button.GONE
            Handler().postDelayed({
                binding.badbutton.visibility = Button.VISIBLE
            }, 5000)
            GlobalScope.launch {
                s =  badwords()
            }
            binding.badtext.text = s
            Log.d("mila", s)
        }
        Log.d("mila", s)
    }

    suspend fun badwords(): String =
        apicall.getPost("en", "json").body()?.insult.toString()

}

