package com.example.pageone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class Userpage_S : AppCompatActivity() {

    lateinit var view1:  Button
    lateinit var homebtn: ImageView
    lateinit var searchbtn: ImageView
    lateinit var calbtn: ImageView
    //lateinit var upgbtn: ImageView

    //database
    lateinit var db: DatabaseHelper

    //@SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userpage_s)

        view1 = findViewById(R.id.viewbtn)

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

        searchbtn = findViewById(R.id.searchbtn)
        searchbtn.setOnClickListener{
            val intent = Intent(this, Search_S::class.java)
            startActivity(intent)
        }

        //initialize database
        db = DatabaseHelper(this)

        view1.setOnClickListener(View.OnClickListener {
            val res = db.getdata()
            if (res.count == 0) {
                Toast.makeText(this@Userpage_S, "No schedules", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            val bfr = StringBuffer()
            while (res.moveToNext()) {
                bfr.append(
                    """
    Date:${res.getString(0)}
    Venue/Event:${res.getString(1)}
    Time:${res.getString(2)}
    
    
    """.trimIndent()
                )
            }
            val bldr = AlertDialog.Builder(this@Userpage_S)
            bldr.setCancelable(true)
            bldr.setTitle("Scheduled Events")
            bldr.setMessage(bfr.toString())
            bldr.show()
        })
    }
}