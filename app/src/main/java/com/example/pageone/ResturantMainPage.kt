package com.example.pageone


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class ResturantMainPage : AppCompatActivity() {

    //lateinit var homebtn: ImageView
    lateinit var searchbtn: ImageView
    lateinit var calbtn: ImageView
    lateinit var upgbtn: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resturant_main_page)

        //homebtn = findViewById(R.id.homebtn)
        searchbtn = findViewById(R.id.searchbtn)
        calbtn = findViewById(R.id.calbtn)
        upgbtn = findViewById(R.id.upgbtn)

        //homebtn.setOnClickListener(View.OnClickListener { openHome_S() })
        searchbtn.setOnClickListener(View.OnClickListener { openSearch_S() })
        calbtn.setOnClickListener(View.OnClickListener { openCalendar_S() })
        upgbtn.setOnClickListener(View.OnClickListener { openUserpage_S() })
    }

    /*fun openHome_S() {
        val intent = Intent(this, MainActivity)
        startActivity(intent)
    }*/

    fun openSearch_S() {
        val intent = Intent(this, Search_S::class.java)
        startActivity(intent)
    }

    fun openCalendar_S() {
        val intent = Intent(this, Calendar_S::class.java)
        startActivity(intent)
    }

    fun openUserpage_S() {
        val intent = Intent(this, Userpage_S::class.java)
        startActivity(intent)
    }
}