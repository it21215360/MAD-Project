package com.example.pageone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.widget.Button
import android.widget.TextView

class home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

       val homebutton = findViewById<Button>(R.id.btn_1)
        homebutton.setOnClickListener {
            val intent = Intent(this, Main::class.java)
            startActivity(intent)
        }

         /*   val homebtn = findViewById<TextView>(R.id.textView5)
            homebtn.setOnClickListener {
                val intent = Intent(this, Hotels::class.java)
                startActivity(intent)
            }*/


        }
}