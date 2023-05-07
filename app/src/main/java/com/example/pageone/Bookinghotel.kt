package com.example.pageone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Bookinghotel : AppCompatActivity() {
    private lateinit var edBookingDate: EditText
    private lateinit var edNoOfDays: EditText
    private lateinit var edNoOfAdults: EditText
    private lateinit var edNoOfChildren: EditText
    private lateinit var spRoomType: Spinner
    private lateinit var btnAdd : Button
    private lateinit var btnView: Button
    private lateinit var btnUpdate: Button

    private lateinit var sqLiteHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: BookingAdapter? = null
    private var std: BookingModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        initView()
        initRecyclerView()
        sqLiteHelper = DatabaseHelper(this)

        btnAdd.setOnClickListener { addBooking() }
        btnView.setOnClickListener { viewBooking() }
        btnUpdate.setOnClickListener { updateBooking() }

        val roomTypeArray: Array<String> = resources.getStringArray(R.array.RoomType)

        adapter?.setOnClickItem{
            edBookingDate.setText(it.booking_date)
            edNoOfDays.setText(it.no_of_days.toString())
            edNoOfAdults.setText(it.no_of_adults.toString())
            edNoOfChildren.setText(it.no_of_children.toString())
            spRoomType.setSelection(roomTypeArray.indexOf(it.room_type))
            std = it
        }

        adapter?.setonClickDeleteItem {
            deleteBooking(it.id)
        }
    }

    private fun initView() {
        edBookingDate = findViewById(R.id.edBookingDate)
        edNoOfDays = findViewById(R.id.edNoOfDays)
        edNoOfAdults = findViewById(R.id.edNoOfAdults)
        edNoOfChildren = findViewById(R.id.edNoOfChildren)
        spRoomType = findViewById(R.id.spRoomType)
        btnAdd = findViewById(R.id.btnAdd)
        btnView = findViewById(R.id.btnView)
        btnUpdate = findViewById(R.id.btnUpdate)
    }

    private fun updateBooking() {
        val bookingDate = edBookingDate.text.toString()
        val noOfDays = edNoOfDays.text.toString()
        val noOfAdults = edNoOfAdults.text.toString()
        val noOfChildren = edNoOfChildren.text.toString()
        val roomType = spRoomType.selectedItem.toString()

        if(bookingDate == std?.booking_date
            && noOfDays == std?.no_of_days.toString()
            && noOfAdults == std?.no_of_adults.toString()
            && noOfChildren == std?.no_of_children.toString()){
            Toast.makeText(this, "Record not changed", Toast.LENGTH_SHORT).show()
            return
        }

        if (std == null) return

        val std = BookingModel(id = std!!.id, booking_date = bookingDate, no_of_days = noOfDays.toInt(),
            no_of_adults = noOfAdults.toInt(), no_of_children = noOfChildren.toInt(), room_type = roomType)
        val status = sqLiteHelper.updateBooking(std)

        if (status > -1){
            clearEditText()
            viewBooking()
        }else{
            Toast.makeText(this, "record updating fail", Toast.LENGTH_SHORT).show()
        }
    }

    private fun viewBooking() {
        val stdList = sqLiteHelper.getAllBookings()

        adapter?.addItems(stdList)
    }

    private fun addBooking() {
        val bookingDate = edBookingDate.text.toString()
        val noOfDays = edNoOfDays.text.toString()
        val noOfAdults = edNoOfAdults.text.toString()
        val noOfChildren = edNoOfChildren.text.toString()
        val roomType = spRoomType.selectedItem.toString()

        if (bookingDate.isEmpty() || noOfDays.isEmpty() || noOfAdults.isEmpty() || noOfChildren.isEmpty()){
            Toast.makeText(this, "please enter required filed", Toast.LENGTH_SHORT).show()
        }else{
            val std = BookingModel(booking_date = bookingDate, no_of_days = noOfDays.toInt(),
                no_of_adults = noOfAdults.toInt(), no_of_children = noOfChildren.toInt(), room_type = roomType)
            val status = sqLiteHelper.insertBooking(std)

            if (status > -1){
                Toast.makeText(this, "Booking added", Toast.LENGTH_SHORT).show()
                clearEditText()
                viewBooking()
            }else{
                Toast.makeText(this, "record added fail", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteBooking(id: Int){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete item?")
        builder.setCancelable(true)
        builder.setPositiveButton("yes"){ dialog, _ ->
            sqLiteHelper.deleteBookingById(id)
            viewBooking()
            dialog.dismiss()
        }
        builder.setNegativeButton("No"){ dialog, _ ->
            dialog.dismiss()
        }

        val alert = builder.create()
        alert.show()
    }

    private fun clearEditText() {
        edBookingDate.setText("")
        edNoOfDays.setText("")
        edNoOfAdults.setText("")
        edNoOfChildren.setText("")
        spRoomType.setSelection(1)
    }

    private fun initRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = BookingAdapter()
        recyclerView.adapter = adapter
    }


}