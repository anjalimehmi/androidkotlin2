package com.o7.androidkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.o7.androidkotlin.databinding.ActivitySignupscreenBinding

class Signupscreen : AppCompatActivity() {
    private lateinit var binding: ActivitySignupscreenBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {0
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignupscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        firebaseAuth= FirebaseAuth.getInstance()

        binding.btnsignup.setOnClickListener {
            var email=binding.edtemail.text.toString()
            var password= binding.edtPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){task ->
                        if (task.isSuccessful){
                            Toast.makeText(this,"Sign up successful", Toast.LENGTH_SHORT).show()
                            var intent= Intent(this, screen4::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this,"signup unsuccessful", Toast.LENGTH_SHORT).show()
                        }

                    }
            }else{
                Toast.makeText(this,"Please enter your password", Toast.LENGTH_SHORT).show()
            }
        }
        binding.Loginhere.setOnClickListener {
            var intent= Intent(this, loginscreen::class.java)
            startActivity(intent)
            finish()
        }



            }
        }
