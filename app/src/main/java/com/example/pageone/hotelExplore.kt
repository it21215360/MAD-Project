package com.example.pageone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView

class hotelExplore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_explore)


        val homeex = findViewById<ImageView>(R.id.homeex)
        homeex.setOnClickListener {
            val intent = Intent(this, home::class.java)
            startActivity(intent)


            val homeex = findViewById<ImageView>(R.id.homeex)
            homeex.setOnClickListener {
                val intent = Intent(this, home::class.java)
                startActivity(intent)

                val loupeex = findViewById<ImageView>(R.id.imageView09)
                loupeex.setOnClickListener {
                    val intent = Intent(this, hotelExplore::class.java)
                    startActivity(intent)

                    val hearttex = findViewById<ImageView>(R.id.imageView9)
                    hearttex.setOnClickListener {
                        val intent = Intent(this, Likehotel::class.java)
                        startActivity(intent)

                        val searchViewhotels: SearchView? =
                            findViewById<SearchView>(R.id.searchViewexplore)
                        if (searchViewhotels != null) {
                            searchViewhotels.setOnQueryTextListener(object :
                                SearchView.OnQueryTextListener {
                                override fun onQueryTextSubmit(query: String?): Boolean {
                                    // Code to handle search query submission
                                    return true
                                }

                                override fun onQueryTextChange(newText: String?): Boolean {
                                    // Code to handle search query text changes
                                    return true
                                }


                            })
                        }
                        findViewById<SearchView>(R.id.searchViewexplore)?.setOnQueryTextListener(
                            object :
                                SearchView.OnQueryTextListener {
                                override fun onQueryTextSubmit(query: String?): Boolean {
                                    val query = query?.trim()
                                    if (!query.isNullOrEmpty()) {
                                        // Code to perform search with the given query
                                    }
                                    return true
                                }

                                override fun onQueryTextChange(newText: String?): Boolean {
                                    val query = newText?.trim()
                                    if (!query.isNullOrEmpty()) {
                                        // Code to filter search results based on the given query
                                    } else {
                                        // Code to reset the search results to the original list
                                    }
                                    return true
                                }
                            })
                    }
                }
            }
        }
    }
}