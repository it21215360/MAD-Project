package com.example.pageone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val homep = findViewById<ImageView>(R.id.homeimgt)
        homep.setOnClickListener {
            val intentp2 = Intent(this, Main::class.java)
            startActivity(intentp2)

            val loupep = findViewById<ImageView>(R.id.heartreda)
            loupep.setOnClickListener {
                val intentp3 = Intent(this, hotelExplore::class.java)
                startActivity(intentp3)

                val heartp = findViewById<ImageView>(R.id.imageView8)
                heartp.setOnClickListener {
                    val intentp4 = Intent(this, Likehotel::class.java)
                    startActivity(intentp4)

                    val profiep = findViewById<ImageView>(R.id.userimgp)
                    profiep.setOnClickListener {
                        val intentp5 = Intent(this, Profile::class.java)
                        startActivity(intentp5)

                    }
                }


            }
        }

    }
}
