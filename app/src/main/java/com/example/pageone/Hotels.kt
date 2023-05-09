package com.example.pageone

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView


class Hotels : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotels)


        val bookhotelbutton = findViewById<Button>(R.id.btnoiu)
        bookhotelbutton.setOnClickListener {
            val intent = Intent(this, Bookinghotel::class.java)
            startActivity(intent)

            val homeimg1 = findViewById<ImageView>(R.id.homeimh)
            homeimg1.setOnClickListener {
                val intent = Intent(this, home::class.java)
                startActivity(intent)

                val loupeimg1 = findViewById<ImageView>(R.id.loupeht)
                loupeimg1.setOnClickListener {
                    val intent = Intent(this, hotelExplore::class.java)
                    startActivity(intent)
                }


            }
        }
    }

}