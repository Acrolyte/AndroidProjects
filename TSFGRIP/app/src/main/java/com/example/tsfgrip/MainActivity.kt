package com.example.tsfgrip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.facebook.AccessTokenTracker
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var textview: TextView
    lateinit var fblogin: LoginButton
    lateinit var callback: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textview = findViewById(R.id.textsign)
        fblogin = findViewById(R.id.fblogin_button)
        callback = CallbackManager.Factory.create()
        fblogin.setPermissions(Arrays.asList("email", "user_birthday"))
        fblogin.registerCallback(callback, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {

            }

            override fun onCancel() {

            }

            override fun onError(error: FacebookException?) {

            }
        })

//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            callbackManager.onActivityResult(requestCode, resultCode, data);
//            super.onActivityResult(requestCode, resultCode, data);
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callback.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    var token : AccessTokenTracker()

}