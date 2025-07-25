package com.o7.androidkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class screen2 : AppCompatActivity() {
    var btn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btn = findViewById(R.id.button4)
        btn?.setOnClickListener {
            Toast.makeText(this, "need to sign up first", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, layout2::class.java)
            startActivity(intent)
        }
        btn = findViewById(R.id.btn4)
        btn?.setOnClickListener {
            var intent = Intent(this, screen3::class.java)
            startActivity(intent)
        }
    }
}