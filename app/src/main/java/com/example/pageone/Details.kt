package com.example.pageone


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Details : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        var button_back_details = findViewById<Button>(R.id.button_back_details)
        button_back_details.setOnClickListener {
            val intent1 = Intent(this, Explore::class.java)
            startActivity(intent1)
        }

        var button_next_details = findViewById<Button>(R.id.button_next_details)
        button_next_details.setOnClickListener{
            val intent2 = Intent(this, Prebook::class.java)
            startActivity(intent2)
            }
        }
}