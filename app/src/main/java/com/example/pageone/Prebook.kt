package com.example.pageone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContentProviderCompat.requireContext

class Prebook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prebook)

        var buttonPrebook = findViewById<Button>(R.id.button_prebook)
        buttonPrebook.setOnClickListener{
            val intent = Intent(this, Booking::class.java)
            startActivity(intent)
            }
        }
}