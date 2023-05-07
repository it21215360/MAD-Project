package com.example.pageone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Search_S : AppCompatActivity() {

    lateinit var homebtn: ImageView
    //lateinit var searchbtn: ImageView
    lateinit var calbtn: ImageView
    lateinit var upgbtn: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_s)

        homebtn = findViewById(R.id.homebtn)
        homebtn.setOnClickListener{
            val intent = Intent(this, ResturantMainPage::class.java)
            startActivity(intent)
        }

        calbtn = findViewById(R.id.calbtn)
        calbtn.setOnClickListener{
            val intent = Intent(this, Calendar_S::class.java)
            startActivity(intent)
        }

        upgbtn = findViewById(R.id.upgbtn)
        upgbtn.setOnClickListener{
            val intent = Intent(this, Userpage_S::class.java)
            startActivity(intent)
        }
    }
}