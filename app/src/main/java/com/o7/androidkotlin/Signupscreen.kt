package com.o7.androidkotlin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.o7.androidkotlin.databinding.ActivitySignupscreenBinding

class Signupscreen : AppCompatActivity() {
    lateinit var binding: ActivitySignupscreenBinding
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val TAG = "LoginActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signupscreen)
        binding = ActivitySignupscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.login.setOnClickListener {
            if (binding.edtemail.text.toString().isNullOrEmpty()) {
                binding.tilemail.isErrorEnabled = true
                binding.tilemail.error = "Enter Email"
            } else if (binding.edtPassword.text.toString().isNullOrEmpty()) {
                binding.tilPassword.isErrorEnabled = true
                binding.tilPassword.error = "Enter Password"
            } else {

                auth.createUserWithEmailAndPassword(
                    binding.edtemail.text.toString(),
                    binding.edtPassword.text.toString()
                )
                    .addOnCompleteListener(this) { task ->
                        val user = auth.currentUser
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Completed Successful${user?.email}", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Registration failed${user?.email}", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnSuccessListener {
                        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT)

                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failed to Register", Toast.LENGTH_SHORT)

                    }
            }
        }
    }
}