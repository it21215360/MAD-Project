package com.example.pageone

import java.util.*

class BookingModel (
    var id: Int = getAutoId(),
    var booking_date: String = "",
    var no_of_days: Int = 0,
    var no_of_adults: Int = 0,
    var no_of_children: Int = 0,
    var room_type: String = ""
){
    companion object{
        fun getAutoId():Int{
            val random = Random()
            return random.nextInt(100)
        }
    }
}