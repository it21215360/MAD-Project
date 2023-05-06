package com.example.pageone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Vans : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vans)

        var rbtn4 = findViewById<Button>(R.id.reservebtn4)
        rbtn4.setOnClickListener {
            val intent = Intent(this, Reservation::class.java)
            startActivity(intent)
        }


        var rbtn5 = findViewById<Button>(R.id.reservebtn5)
        rbtn5.setOnClickListener {
            val intent = Intent(this, Reservation::class.java)
            startActivity(intent)
        }
    }


}