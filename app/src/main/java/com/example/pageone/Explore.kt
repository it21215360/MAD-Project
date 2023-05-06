package com.example.pageone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Explore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        var button1_explore = findViewById<Button>(R.id.button1_explore)
        button1_explore.setOnClickListener {
            val intent12 = Intent(this, Details::class.java)
            startActivity(intent12)
            }
        }
}