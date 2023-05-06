package com.example.pageone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Button


class SafariMainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safari_main_page)

        var button_main = findViewById<Button>(R.id.button_main)
        button_main.setOnClickListener{
            val intent1 = Intent(this , Explore::class.java)
            startActivity(intent1)
            }

    }
}