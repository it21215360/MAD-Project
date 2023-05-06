package com.example.pageone

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Thank : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thank)

        var button_thank = findViewById<Button>(R.id.button_thank)
        button_thank.setOnClickListener{
            var intentThank= Intent(this, Explore::class.java)
            startActivity(intentThank)
            }
        }
}