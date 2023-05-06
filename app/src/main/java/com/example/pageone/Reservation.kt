package com.example.pageone

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import java.text.SimpleDateFormat
import java.util.*

class Reservation : AppCompatActivity() {

    private lateinit var id: ConstraintLayout
    private lateinit var vehicleTypeTab: EditText
    private lateinit var vehicleNameTab: EditText
    private lateinit var needADriverCheckBox: CheckBox
    private lateinit var fromDate: EditText
    private lateinit var numbers: Spinner
    private lateinit var totalAmount: TextView
    private lateinit var AddReservation: Button
    private lateinit var viewReservation: Button
    private lateinit var update: Button
    private lateinit var delete: Button

    internal var dbHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        id = findViewById<ConstraintLayout>(R.id.id2)
        vehicleTypeTab = findViewById<EditText>(R.id.vehicleTypeTab)
        vehicleNameTab = findViewById<EditText>(R.id.vehicleNameTab)
        needADriverCheckBox = findViewById<CheckBox>(R.id.needADriverCheckBox)
        fromDate = findViewById<EditText>(R.id.fromDate)
        numbers = findViewById<Spinner>(R.id.numbers)
        totalAmount = findViewById<TextView>(R.id.totalAmount)
        AddReservation = findViewById<Button>(R.id.AddReservation)
        viewReservation = findViewById<Button>(R.id.viewReservation)
        update = findViewById<Button>(R.id.updateSafari)
        delete = findViewById<Button>(R.id.deleteSafari)

        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(calendar)
        }

        fromDate.setOnClickListener{
            DatePickerDialog(this, datePicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        handleInserts()
        handleUpdates()
        handleViewing()
        handleDelete()

        val finishBtn = findViewById<Button>(R.id.nextSuccess)
        finishBtn.setOnClickListener{
            val intent2 = Intent(this, SuccessPage::class.java)
            startActivity(intent2)
        }

    }

    private fun updateLable(calendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        fromDate.setText(sdf.format(calendar.time))
    }

    fun showToast(text: String) {
        Toast.makeText(this@Reservation, text, Toast.LENGTH_LONG).show()
    }

    fun showDialog(title: String, Message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }

    fun clearEditTexts() {
        vehicleTypeTab.setText("")
        vehicleNameTab.setText("")
        needADriverCheckBox.isChecked = false
        fromDate.setText("")
        numbers.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListOf<String>())
    }



    fun handleInserts() {
        AddReservation.setOnClickListener {
            try {
                val vehicleType = vehicleTypeTab.text.toString()
                val vehicleName = vehicleNameTab.text.toString()
                val hasDriver = needADriverCheckBox.isChecked
                val fromDate = fromDate.text.toString()
                val numberOfDays = numbers.selectedItem.toString().toInt()


                var price = dbHelper.calculateVehiclePrice(vehicleName)
                if (hasDriver) {
                    price = 5000 +(numberOfDays * price)
                }else {
                    price = (numberOfDays * price)
                }

                dbHelper.addReservation(vehicleType, vehicleName, hasDriver.toString(), fromDate, numberOfDays)
                totalAmount.text = "$price Rupeese"
                clearEditTexts()
            } catch (e: Exception) {
                e.printStackTrace()
                showToast(e.message.toString())
            }
        }
    }

    fun handleUpdates() {
        update.setOnClickListener {

            try {
                val id = id.tag.toString()
                val vehicleType = vehicleTypeTab.text.toString()
                val vehicleName = vehicleNameTab.text.toString()
                val hasDriver = needADriverCheckBox.isChecked
                val fromDate = fromDate.text.toString()
                val numberOfDays = numbers.selectedItem.toString().toInt()


                var price = dbHelper.calculateVehiclePrice(vehicleName)
                if (hasDriver) {
                    price = 5000 +(numberOfDays * price)
                }else {
                    price = (numberOfDays * price)
                }
                var isUpdate = dbHelper.updateReservation(id,vehicleType, vehicleName, hasDriver.toString(), fromDate, numberOfDays)
                totalAmount.text = "$price Rupeese"
                clearEditTexts()
                /*val isUpdate = dbHelper.updateReservation(
                    id.tag.toString(),
                    vehicleTypeTab.text.toString(),
                    vehicleNameTab.text.toString(),
                    needADriverCheckBox.isChecked.toString(),
                    fromDate.text.toString(),
                    numbers.selectedItem.toString().toInt()
                )*/
                if (isUpdate == true) {
                    showToast("Data Updated Successfully")
                } else {
                    showToast("Data Not Updated")
                }

            } catch (e: Exception) {
                e.printStackTrace()
                showToast(e.message.toString())
            }
        }
    }

    fun handleDelete() {
        delete.setOnClickListener {
            try {
                dbHelper.deleteReservation(id.tag.toString())
                clearEditTexts()
            }catch (e: Exception) {
                e.printStackTrace()
                showToast(e.message.toString())
            }
        }
    }

    fun handleViewing() {
        viewReservation.setOnClickListener {
            val res = dbHelper.allReservations
            if (res.count == 0) {
                showDialog("Error", "No Data Found")
                return@setOnClickListener
            }
            val buffer = StringBuffer()
            while (res.moveToNext()) {
                buffer.append("ID: " + res.getString(0) + "\n")
                buffer.append("TYPE: " + res.getString(1) + "\n")
                buffer.append("NAME: " + res.getString(2) + "\n")
                buffer.append("DRIVER: " + res.getString(3) + "\n")
                buffer.append("DATE: " + res.getString(4) + "\n")
                buffer.append("DAYS: " + res.getString(5) + "\n")
                val hasDriver = res.getString(3).toBoolean()
                val numberOfDays = res.getString(5).toInt()
                var price = dbHelper.calculateVehiclePrice(res.getString(2))
                if (hasDriver) {
                    price = 5000 + (numberOfDays * price)
                } else {
                    price = numberOfDays * price
                }
                buffer.append("Total Price: $price Rupees\n\n")
                id.tag = res.getString(0)
            }
            showDialog("Data Listing", buffer.toString())
        }
    }



}