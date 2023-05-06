package com.example.pageone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val secondActButton = findViewById<Button>(R.id.car)
        secondActButton.setOnClickListener{
            //connecting pages
            val Intent = Intent(this,Cars::class.java)
            startActivity(Intent)

        }

        val thirdActButton = findViewById<Button>(R.id.van)
        thirdActButton.setOnClickListener{
            val Intent2 = Intent(this,Vans::class.java)
            startActivity(Intent2)
        }




    }
}