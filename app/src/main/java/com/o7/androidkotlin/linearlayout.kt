package com.o7.androidkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class linearlayout : AppCompatActivity() {
    var btn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.linearlayout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btn = findViewById(R.id.button2)
        btn?.setOnClickListener {
            Toast.makeText(this, "Loading....", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, screen1::class.java)
            startActivity(intent)
        }
        btn = findViewById(R.id.button3)
        btn?.setOnClickListener {
            Toast.makeText(this, "fill your details", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, linearlayout::class.java)
            startActivity(intent)
        }
    }
}