package com.o7.androidkotlin

import android.R.attr.password
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.o7.androidkotlin.databinding.ActivityLoginscreenBinding

class loginscreen : AppCompatActivity() {
    private lateinit var binding: ActivityLoginscreenBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        firebaseAuth = FirebaseAuth.getInstance()
        binding.apply {
            btnlogin.setOnClickListener {
                if (email.text.toString().isEmpty()) {
                    email.error = "Please enter your email"
                } else if (pass.text.toString().isEmpty())else {
                    val emailtext = binding.email.text.toString().trim()
                    val passwordtext =binding.pass.text.tostring().trim()
                    auth.signInWithEmailAndPassword(email,password)
                        .addOnSuccessListener {
                            Toast.makeText(
                                this@loginscreen,
                                "user login successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener {e->
                            Toast.makeText(this@loginscreen, "login failed", Toast.LENGTH_SHORT)
                                .show()

                        }
                }
            }
        }
    }
}