package com.dsckiet.ytvid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.color.CustomColor
import com.example.ytVid.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
//    private lateinit var oneTapClient: SignInClient
//    private lateinit var signInRequest: BeginSignInRequest
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
//    private val REQ_ONE_TAP = 1
//    private val TAG = "dekho"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth =  FirebaseAuth.getInstance()

        val googleSignInOptions = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        ).requestIdToken("327109102615-9g9eukqurutfif4u6pfnfcem1inueb91.apps.googleusercontent.com")
            .requestEmail().build()

        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions)
        //
        // check for current logged in user
        // if logged in, move to next fragment
        //

        binding.btSignIn.setOnClickListener(View.OnClickListener {
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, 100)
        })

        CustomColor.Green("Shivendu hara", 0, 7,binding.tvEmail )


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            val signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            if (signInAccountTask.isSuccessful) {
                val s = "Google sign in Successful"
                displayToast(s)

                try {
                    val googleSignInAccount = signInAccountTask.getResult(
                        ApiException::class.java
                    )
                    if (googleSignInAccount != null) {
                        val authCredential =
                            GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
                        firebaseAuth.signInWithCredential(authCredential)
                            .addOnCompleteListener(this,
                                OnCompleteListener<AuthResult?> { task ->
                                    if (task.isSuccessful) {
                                        binding.tvEmail.text = firebaseAuth.currentUser?.email.toString()
                                        //new activity
//                                        startActivity(
//                                            Intent(
//                                                this@MainActivity,
//                                                ProfileActivity::class.java
//                                            ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                        )
                                        displayToast("Firebase Authentication Successful")
//                                        finish()
                                    } else {
                                        displayToast("Firebase Authentication failed! " + task.exception!!.message)
                                    }
                                })
                    }
                } catch (e: ApiException) {
                    e.printStackTrace()
                }
            }
        }
//        cb.onActivityResult(requestCode, resultCode, data)
    }


    private fun displayToast(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()
    }
    // ...
}




