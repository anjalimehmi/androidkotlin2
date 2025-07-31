package com.o7.androidkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.o7.androidkotlin.databinding.ActivityMainBinding

class Linearlayout : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var btn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btn = findViewById(R.id.button2)
        Glide.with(this)
            .load("https://thumbs.dreamstime.com/b/beautiful-rain-forest-ang-ka-nature-trail-doi-inthanon-national-park-thailand-36703721.jpg")
            .into(binding.glideimgview);
        btn?.setOnClickListener {
            Toast.makeText(this, "Loading....", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, screen1::class.java)
            startActivity(intent)
        }
        btn = findViewById(R.id.button3)
        btn?.setOnClickListener {
            Toast.makeText(this, "fill your details", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, layout2::class.java)
            startActivity(intent)
        }
        btn = findViewById<Button>(R.id.btnRecyclerActivity)
        btn?.setOnClickListener {
            var intent = Intent(this, RecyclerActivity::class.java)
            startActivity(intent)
        }
        btn = findViewById<Button>(R.id.firestorebtn)
        btn?.setOnClickListener {
            var intent = Intent(this, Firestore::class.java)
            startActivity(intent)
        }
        btn = findViewById<Button>(R.id.loginsc)
        btn?.setOnClickListener {
            var intent = Intent(this, loginscreen::class.java)
            startActivity(intent)
        }
        btn = findViewById<Button>(R.id.btnbottomnav)
        btn?.setOnClickListener {
            var intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }
        btn = findViewById<Button>(R.id.gifbtn)
        btn?.setOnClickListener {
            var intent = Intent(this, gif_screen::class.java)
            startActivity(intent)
        }
    }
}