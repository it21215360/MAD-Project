package com.example.pageone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Cars : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cars)

        val btnfour : Button  = findViewById(R.id.seats4)
        val fourseats = FourSeats()

        val btnmore : Button  = findViewById(R.id.more)
        val moreseats = MoreSeats()

        btnfour.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView5, fourseats)
                commit()
            }
        }


        btnmore.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView5, moreseats)
                commit()
            }
        }


    }


}