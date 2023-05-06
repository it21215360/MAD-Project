package com.example.pageone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class Hotels : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotels)



        val bookhotelbutton = findViewById<Button>(R.id.btnoiu)
        bookhotelbutton.setOnClickListener {
            val intent = Intent(this, Bookinghotel::class.java)
            startActivity(intent)


            }
        }

}