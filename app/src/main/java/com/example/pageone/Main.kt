package com.example.pageone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.widget.Button


class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val bookbutton = findViewById<Button>(R.id.bookbtn)
        bookbutton.setOnClickListener {
            val intent = Intent(this, Hotels::class.java)
            startActivity(intent)

            }
        }
}