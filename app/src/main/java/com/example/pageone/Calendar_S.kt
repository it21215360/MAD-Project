package com.example.pageone


import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class Calendar_S : AppCompatActivity() {

    //navigation buttons
    lateinit var homebtn: ImageView
    lateinit var searchbtn: ImageView
    lateinit var upgbtn: ImageView

    //calendar
    lateinit var calendarView2: CalendarView
    lateinit var calendar: Calendar
    lateinit var etxt: EditText
    lateinit var etxttime: EditText
    lateinit var insert: Button
    lateinit var update: Button
    lateinit private var dateSelected: String
    lateinit private var caltv: TextView

    //database
    lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_s)

        homebtn = findViewById(R.id.homebtn)
        homebtn.setOnClickListener {
            val intent = Intent(this, ResturantMainPage::class.java)
            startActivity(intent)
        }

        searchbtn = findViewById(R.id.searchbtn)
        searchbtn.setOnClickListener {
            val intent = Intent(this, Calendar_S::class.java)
            startActivity(intent)
        }

        upgbtn = findViewById(R.id.upgbtn)
        upgbtn.setOnClickListener {
            val intent = Intent(this, Userpage_S::class.java)
            startActivity(intent)
        }

        //calendar
        etxt = findViewById(R.id.etxt)
        etxttime = findViewById(R.id.etxttime)
        calendarView2 = findViewById(R.id.calendarView2)
        calendar = Calendar.getInstance()
        insert = findViewById(R.id.subbtn)
        update = findViewById(R.id.subbtnupdate)
        setDate(1, 5, 2023)
        date
        calendarView2.setOnDateChangeListener(CalendarView.OnDateChangeListener { view, year, month, day ->
            Toast.makeText(
                this@Calendar_S,
                day.toString() + "/" + (month + 1) + "/" + year,
                Toast.LENGTH_LONG
            ).show()
        })

        //initialize database
        db = DatabaseHelper(this)

        // create listeners
        insert.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                //get the date from the calendar view ->
                val dt: String = date
                val schdl = etxt.getText().toString()
                val tm = etxttime.getText().toString()
                val checkinsertdata = db.insertuserdata(dt, schdl, tm)
                if (checkinsertdata == true) {
                    Toast.makeText(this@Calendar_S, "Day Scheduled", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@Calendar_S, "Error Occured", Toast.LENGTH_SHORT).show()
                }
            }
        })
        update.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                //get the date from the calendar view ->
                val dt: String = date
                val schdl = etxt.getText().toString()
                val tm = etxttime.getText().toString()
                val checkupdatedata = db.updateuserdata(dt, schdl, tm)
                if (checkupdatedata == true) {
                    Toast.makeText(this@Calendar_S, "Schedule Updated", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@Calendar_S, "Error Occured", Toast.LENGTH_SHORT).show()
                }
            }
        })




    }
    //Toast.makeText(this,dateSelected,Toast.LENGTH_SHORT).show();
    val date: String
        get() {
            val date = calendarView2.date
            val simpleDateFormat =
                SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())
            calendar.timeInMillis = date
            //Toast.makeText(this,dateSelected,Toast.LENGTH_SHORT).show();
            return simpleDateFormat.format(calendar.time)
        }

    private fun setDate(day: Int, month: Int, year: Int) {
        calendar[Calendar.DAY_OF_MONTH] = day
        calendar[Calendar.MONTH] = month - 1
        calendar[Calendar.YEAR] = year
        val milli = calendar.timeInMillis
        calendarView2.date = milli
    }

    private fun dispaydata() {
        val c: Cursor = db.getdate()
        if (c.getCount() === 0) {
            caltv.text = "No Schedule"
            return
        } else {
            caltv.text = db.getdate().toString()
        }
    }




}
