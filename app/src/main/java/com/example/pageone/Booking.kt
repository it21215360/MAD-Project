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
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import java.text.SimpleDateFormat
import java.util.*

class Booking : AppCompatActivity() {

    private lateinit var id: ConstraintLayout
    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var date: EditText
    private lateinit var num_adult: Spinner
    private lateinit var num_kids: Spinner
    private lateinit var checkBox : CheckBox
    private lateinit var submit: Button
    private lateinit var view: Button
    private lateinit var update: Button
    private lateinit var delete: Button

    internal var dbHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        id = findViewById<ConstraintLayout>(R.id.newid)
        name = findViewById<EditText>(R.id.name)
        phone = findViewById<EditText>(R.id.phone)
        date = findViewById<EditText>(R.id.date)
        num_adult = findViewById<Spinner>(R.id.num_adult)
        num_kids = findViewById<Spinner>(R.id.num_kids)
        checkBox = findViewById<CheckBox>(R.id.checkBox)
        submit = findViewById<Button>(R.id.submit)
        view = findViewById<Button>(R.id.view2)
        update = findViewById<Button>(R.id.updateSafari)
        delete = findViewById<Button>(R.id.deleteSafari)

        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(calendar)
        }

        date.setOnClickListener{
            DatePickerDialog(this, datePicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(
                Calendar.DAY_OF_MONTH)).show()
        }

            handleInserts()
            handleUpdates()
            handleViewing()
            handleDelete()

        val nextBtn = findViewById<Button>(R.id.buttonnxt)
        nextBtn.setOnClickListener {
            val intentnext = Intent(this, Thank::class.java)
            startActivity(intentnext)

        }

    }

    private fun updateLable(calendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        date.setText(sdf.format(calendar.time))
    }
    fun showToast(text: String) {
        Toast.makeText(this@Booking, text, Toast.LENGTH_LONG).show()
    }

    fun showDialog(title: String, Message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }

    fun clearEditTexts() {
        name.setText("")
        phone.setText("")
        date.setText("")

        num_adult.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListOf<String>())
        num_kids.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListOf<String>())
    }

    fun handleInserts() {
        submit.setOnClickListener {
            try {
                val name = name.text.toString()
                val phone = phone.text.toString()
                val date = date.text.toString()
                val num_adult = num_adult.selectedItem.toString().toInt()
                val num_kids = num_kids.selectedItem.toString().toInt()
                val guide = checkBox.isChecked

                dbHelper.addReservationSafari(name, phone, date, num_adult, num_kids, guide.toString())
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
               val isUpdate = dbHelper.updateBookingSafari(
                   id.tag.toString(),
                   name.text.toString(),
                   phone.text.toString(),
                   date.text.toString(),
                   num_adult.selectedItem.toString().toInt(),
                   num_kids.selectedItem.toString().toInt(),
                   checkBox.isChecked.toString()

               )
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
               dbHelper.deleteBookingSafari(id.tag.toString())
               clearEditTexts()
           }catch (e: Exception) {
               e.printStackTrace()
               showToast(e.message.toString())
           }
       }
   }

   fun handleViewing() {
       view.setOnClickListener {
           val res = dbHelper.allSafariBookings
           if (res.count == 0) {
               showDialog("Error", "No Data Found")
               return@setOnClickListener
           }
           val buffer = StringBuffer()
           while (res.moveToNext()) {
               buffer.append("ID: " + res.getString(0) + "\n")
               buffer.append("NAME: " + res.getString(1) + "\n")
               buffer.append("PHONE: " + res.getString(2) + "\n")
               buffer.append("DATE: " + res.getString(3) + "\n")
               buffer.append("NUMBER OF ADULTS: " + res.getString(4) + "\n")
               buffer.append("NUMBER OF KIDS: " + res.getString(5) + "\n")
               buffer.append("TOUR GUIDE: " + res.getString(6) + "\n")
               id.tag = res.getString(0)
           }
           showDialog("Data Listing", buffer.toString())
  }
}

}