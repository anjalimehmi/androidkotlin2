package com.o7.androidkotlin

import android.R.attr.password
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.Button
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
        binding.btnlogin.setOnClickListener {

            val email=binding.email.text.toString()
            val password=binding.edtPassword.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, password)


                    .addOnCompleteListener(this){task ->

                        if (task.isSuccessful){
                            Toast.makeText(this,"Login successful", Toast.LENGTH_SHORT).show()
                            val intent= Intent(this, screen4::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this,"Login failed", Toast.LENGTH_SHORT).show()
                        }
                    }

            }else
            {
                Toast.makeText(this,"Please enter your email and password", Toast.LENGTH_SHORT).show()
            }
            }
        }
    }
