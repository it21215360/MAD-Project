package com.example.pageone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.widget.Button
import android.widget.ImageView


class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val bookbutton = findViewById<Button>(R.id.bookbtn2)
        bookbutton.setOnClickListener {
            val intenth1 = Intent(this, Marinohotel::class.java)
            startActivity(intenth1)

            val bookBtnhotel = findViewById<Button>(R.id.bookbtn)
            bookBtnhotel.setOnClickListener {
                val intenth2 = Intent(this, Hotels::class.java)
                startActivity(intenth2)


                val homeloupe = findViewById<ImageView>(R.id.loupeimg)
                homeloupe.setOnClickListener {
                    val intentsearch = Intent(this, hotelExplore::class.java)
                    startActivity(intentsearch)

                    val homeloupe = findViewById<ImageView>(R.id.redhearthome)
                    homeloupe.setOnClickListener {
                        val intent = Intent(this, Likehotel::class.java)
                        startActivity(intent)

                        val homeuserp = findViewById<ImageView>(R.id.userimg)
                        homeuserp.setOnClickListener {
                            val intent = Intent(this, Profile::class.java)
                            startActivity(intent)

                            val homelikep1 = findViewById<ImageView>(R.id.imageView3)
                            homelikep1.setOnClickListener {
                                val intent = Intent(this, Likehotel::class.java)
                                startActivity(intent)

                                val homelikep2 = findViewById<ImageView>(R.id.imageView2)
                                homelikep2.setOnClickListener {
                                    val intent = Intent(this, Likehotel::class.java)
                                    startActivity(intent)


                                }
                            }
                        }
                    }

                }
            }
        }
    }
}