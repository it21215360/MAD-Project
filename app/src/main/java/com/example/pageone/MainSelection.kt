package com.example.pageone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainSelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_selection)

        val vehicle = findViewById<Button>(R.id.vehicleMainPage)
        vehicle.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        val safariPage = findViewById<Button>(R.id.safari)
        safariPage.setOnClickListener {
            val intent2 = Intent(this, SafariMainPage::class.java)
            startActivity(intent2)
        }

        val homebtn = findViewById<Button>(R.id.hotelsHome)
        homebtn.setOnClickListener {
            val intent3 = Intent(this, home::class.java)
            startActivity(intent3)
        }
    }
}