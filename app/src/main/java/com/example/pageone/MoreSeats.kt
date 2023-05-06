package com.example.pageone

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class MoreSeats : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_more_seats, container, false)
        val rbtn1 = view.findViewById<Button>(R.id.reservebtn3)

        rbtn1.setOnClickListener {
            val intent = Intent(requireContext(), Reservation::class.java)
            startActivity(intent)
        }

        return view
        // Inflate the layout for this fragment

    }


}