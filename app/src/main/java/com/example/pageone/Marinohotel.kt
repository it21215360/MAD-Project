package com.example.pageone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class Marinohotel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marinohotel)


        val marinoho = findViewById<Button>(R.id.btnoiu)
        marinoho.setOnClickListener {
            val intent = Intent(this, Bookinghotel::class.java)
            startActivity(intent)


            val backh2m = findViewById<ImageView>(R.id.imagearrowace)
            backh2m.setOnClickListener {
                val intenthm = Intent(this, Main::class.java)
                startActivity(intenthm)


                val marihome = findViewById<ImageView>(R.id.homeimhace)
                marihome.setOnClickListener {
                    val intentm = Intent(this, Main::class.java)
                    startActivity(intentm)


                    val mariloup = findViewById<ImageView>(R.id.loupeaceht)
                    mariloup.setOnClickListener {
                        val intent = Intent(this, hotelExplore::class.java)
                        startActivity(intent)


                        val mariheart = findViewById<ImageView>(R.id.heartredace)
                        mariheart.setOnClickListener {
                            val intenthm = Intent(this, Likehotel::class.java)
                            startActivity(intenthm)


                            val mariuser = findViewById<ImageView>(R.id.userimg3ace)
                            mariuser.setOnClickListener {
                                val intent = Intent(this, Profile::class.java)
                                startActivity(intent)
                            }
                        }
                    }}}}

    }
}