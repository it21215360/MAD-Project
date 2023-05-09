package com.example.pageone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class hotelbookingconfirm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotelbookingconfirm)


        val hotelconfirm = findViewById<Button>(R.id.hotelconbtn)
        hotelconfirm.setOnClickListener {
            val intent = Intent(this, Main::class.java)
            startActivity(intent)
        }



    }
}