package com.example.pageone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Likehotel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_likehotel)


        val homelike = findViewById<ImageView>(R.id.imageView89)
        homelike.setOnClickListener {
            val intentl1 = Intent(this, Main::class.java)
            startActivity(intentl1)

            val louplike = findViewById<ImageView>(R.id.imageView095)
            louplike.setOnClickListener {
                val intentl2 = Intent(this, hotelExplore::class.java)
                startActivity(intentl2)

                val heartlike = findViewById<ImageView>(R.id.imageView90)
                heartlike.setOnClickListener {
                    val intentl3 = Intent(this, Likehotel::class.java)
                    startActivity(intentl3)

                    val profilelike = findViewById<ImageView>(R.id.imageView616)
                    profilelike.setOnClickListener {
                        val intentl4 = Intent(this, Profile::class.java)
                        startActivity(intentl4)

                    }


                }
            }

        }
    }}