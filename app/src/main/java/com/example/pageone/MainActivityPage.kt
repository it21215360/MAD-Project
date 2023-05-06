package com.example.pageone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivityPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_page)

        val start = findViewById<Button>(R.id.startBtn)
        start.setOnClickListener{
            //connecting pages
            val intent = Intent(this,Login::class.java)
            startActivity(intent)

            }
    }
}