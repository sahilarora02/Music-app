package com.example.apnamusic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
           supportActionBar?.hide()
        Handler().postDelayed({
            var intent = Intent(this , HomeActivity::class.java)
            startActivity(intent)
        } , 1500)

    }
}