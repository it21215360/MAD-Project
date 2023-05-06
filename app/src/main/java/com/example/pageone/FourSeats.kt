package com.example.pageone

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button



/*class FourSeats : Fragment() {
    //private lateinit var reservebtn1: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

      return  inflater.inflate(R.layout.fragment_four_seats, container, false)


    }

    val rbtn1 = findViewById<Button>(R.id.reservebtn1)
    rbtn1.setOnClickListener{
        val intent = Intent(this, Reservation::class.java)
        startActivity(intent)
    }


}*/

class FourSeats : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_four_seats, container, false)
        val rbtn1 = view.findViewById<Button>(R.id.reservebtn1)
        val rbtn2 = view.findViewById<Button>(R.id.reservebtn2)
        rbtn1.setOnClickListener {
            val intent = Intent(requireContext(), Reservation::class.java)
            startActivity(intent)
        }
        rbtn2.setOnClickListener {
            val intent2 = Intent(requireContext(), Reservation::class.java)
            startActivity(intent2)
        }
        return view
    }
}
