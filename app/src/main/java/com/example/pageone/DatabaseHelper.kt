package com.example.pageone

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

  companion object {

      private const val DATABASE_VERSION = 1
      private const val DATABASE_NAME = "booking.db"

      //USER LOGIN
      private const val TABLE_NAME_LOGIN = "UserData"
      private const val USERNAME = "user_Name"
      private const val PASSWORD = "user_pass"
      private const val E_MAIL = "user_email"

      //SELECT VEHICLE - IT21215360
      private const val TABLE_NAME = "reservation_table"
      private const val COL_ID = "id"
      private const val COL_VEHICLE_TYPE = "vehicle_type"
      private const val COL_VEHICLE_NAME = "vehicle_name"
      private const val COL_NEED_DRIVER = "need_driver"
      private const val COL_FROM_DATE = "from_date"
      private const val COL_NUMBER_OF_DAYS = "number_of_days";
      private val vehiclePrices = mapOf(
          "Mercedes A200" to 44000,
          "AudiA4 White" to 40000,
          "Audi Q7" to 80000,
          "TOYOTA KDH 223" to 70000,
          "TOYOTA  ALPHARD" to 80000
      )

      //SAFARI - IT21197000
      private const val TABLE_NAME_SAFARI = "safari_table"
      private const val ID_SAFARI = "id"
      private const val NAME_SAFARI = "name"
      private const val PHONE_SAFARI = "phone"
      private const val DATE_SAFARI = "date"
      private const val NUM_ADULT_SAFARI="num_adult"
      private const val NUM_KID_SAFARI="num_kids"
      private const val TOUR_GUIDE_SAFARI = "giude"

      //HOTELS - IT21307362
      private const val TABLE_BOOKING = "booking"
      private const val ID = "id"
      private const val BOOKING_DATE = "booking_date"
      private const val NO_OF_DAYS = "no_of_days"
      private const val NO_OF_ADULTS = "no_of_adults"
      private const val NO_OF_CHILDREN = "no_of_children"
      private const val ROOM_TYPE = "room_type"


  }




    override fun onCreate(db: SQLiteDatabase?) {
        //SIGN UP & LOGIN
        val createTableSignup = "CREATE TABLE $TABLE_NAME_LOGIN ($USERNAME TEXT PRIMARY KEY," +
                "$E_MAIL TEXT," +
                "$PASSWORD TEXT" + ")"
        db?.execSQL(createTableSignup)

        //SELECT VEHICLE - IT21215360
        val createTable = "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COL_VEHICLE_TYPE TEXT," +
                "$COL_VEHICLE_NAME TEXT," +
                "$COL_NEED_DRIVER TEXT," +
                "$COL_FROM_DATE TEXT," +
                "$COL_NUMBER_OF_DAYS INTEGER" + ")"
        db?.execSQL(createTable)

        //SAFARI - IT21197000
        val createTableSafari = "CREATE TABLE $TABLE_NAME_SAFARI ($ID_SAFARI INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$NAME_SAFARI TEXT," +
                "$PHONE_SAFARI TEXT," +
                "$DATE_SAFARI TEXT," +
                "$NUM_ADULT_SAFARI INTEGER," +
                "$NUM_KID_SAFARI INTEGER," +
                "$TOUR_GUIDE_SAFARI TEXT" + ")"
        db?.execSQL(createTableSafari)

        //HOTEL -IT21307362
        val createTblStudent = ("CREATE TABLE " + TABLE_BOOKING + "("
                + ID + " INTEGER PRIMARY KEY," + BOOKING_DATE + " TEXT," + NO_OF_DAYS + " INTEGER,"
                + NO_OF_ADULTS + " INTEGER," + NO_OF_CHILDREN + " INTEGER," + ROOM_TYPE + " TEXT"
                + ")")
        db?.execSQL(createTblStudent)

        //RESTURANTS - IT21
        db?.execSQL("create Table Res_Schedules(Date TEXT primary key, Restaurant TEXT,time TEXT)")

    }




    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_LOGIN")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_SAFARI")

        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_BOOKING")

        db.execSQL("drop Table if exists Res_Schedules")
        onCreate(db)


        onCreate(db)
    }

    //INSERT LOGIN DATA
    fun insertUserData(username: String, email: String, password: String) : Boolean{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(USERNAME, username)
        cv.put(E_MAIL, email)
        cv.put(PASSWORD, password)
        val result = db.insert(TABLE_NAME_LOGIN,null,cv)

        return result != (-1).toLong()
    }

    //CHECK USERNAME & PASSWORD
    fun checkPass(username: String, password: String): Boolean{
        val db = this.writableDatabase
        val query = "SELECT * FROM $TABLE_NAME_LOGIN WHERE $USERNAME = '$username' AND $PASSWORD = '$password'"
        val cursor = db.rawQuery(query,null)
        if (cursor.count<=0){
            cursor.close()
            return false
        }
            return true
    }

    //VIEW ALL SAFARI BOOKINGS - IT21197000
    val allSafariBookings : Cursor
        get() {
            val db = this.writableDatabase
            val res = db.rawQuery("SELECT * FROM " + TABLE_NAME_SAFARI, null)
            return res
        }

    //BOOK SAFARI - IT21197000
    fun addReservationSafari(name: String,phone: String, date: String, num_adult: Int, num_kids: Int, guide: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME_SAFARI, name)
        values.put(PHONE_SAFARI, phone)
        values.put(DATE_SAFARI, date)
        values.put(NUM_ADULT_SAFARI, num_adult)
        values.put(NUM_KID_SAFARI, num_kids)
        values.put(TOUR_GUIDE_SAFARI, guide)



        db.insert(TABLE_NAME_SAFARI,null,values)

    }

        //UPDATE SAFARI - IT21197000
        fun updateBookingSafari(id: String, name: String, phone: String, date: String, num_adult: Int, num_kids: Int, guide: String): Boolean {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(ID_SAFARI, id)
            values.put(NAME_SAFARI, name)
            values.put(PHONE_SAFARI, phone)
            values.put(DATE_SAFARI, date)
            values.put(NUM_ADULT_SAFARI, num_adult)
            values.put(NUM_KID_SAFARI, num_kids)
            values.put(TOUR_GUIDE_SAFARI, guide)

            val _success = db.update(TABLE_NAME_SAFARI,values, "$ID_SAFARI  =?",  arrayOf(id))
            return true
        }


    //DELETE SAFARI BOOKING - IT21197000
    fun deleteBookingSafari(_id: String):Boolean{
        val db = this.writableDatabase
        val _success =  db.delete(TABLE_NAME_SAFARI, "$ID_SAFARI  =?" , arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }


    //VIEW All RESERVATIONS - IT21215360
    val allReservations : Cursor
        get() {
            val db = this.writableDatabase
            val res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
        return res
        }


    //INSERT RESERVATIONS - IT21215360
    fun addReservation(type: String,name: String, driver: String, date: String, days: Int) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_VEHICLE_TYPE, type)
        values.put(COL_VEHICLE_NAME, name)
        values.put(COL_NEED_DRIVER, driver)
        values.put(COL_FROM_DATE, date)
        values.put(COL_NUMBER_OF_DAYS, days)

        db.insert(TABLE_NAME,null,values)
    }




    //VEHICLE INITIAL PRICE GETTER -  IT21215360
    fun calculateVehiclePrice(vehicleName: String): Int {
        return vehiclePrices[vehicleName] ?: 0
    }

    //VEHICLE RESERVATION DELETE - IT21215360
    fun deleteReservation(_id: String):Boolean{
        val db = this.writableDatabase
        val _success =  db.delete(TABLE_NAME, "$COL_ID  =?" , arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

    //VEHICLE RESERVATION UPDATE - IT21215360
    fun updateReservation(id: String, type: String, name: String, driver: String, date: String, days: Int): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, id)
        values.put(COL_VEHICLE_TYPE, type)
        values.put(COL_VEHICLE_NAME, name)
        values.put(COL_NEED_DRIVER, driver)
        values.put(COL_FROM_DATE, date)
        values.put(COL_NUMBER_OF_DAYS, days)

        val _success = db.update(TABLE_NAME,values, "$COL_ID  =?",  arrayOf(id))
        return true
    }

    //VEHICLE LAST RESERVATION ID - IT21215360
    fun getLastInsertedId(): Int {
        var id = -1
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT last_insert_rowid()", null)
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0)
        }
        cursor.close()
        return id
    }


    //HOTEL - IT21307362
    fun insertBooking(std: BookingModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(BOOKING_DATE, std.booking_date)
        contentValues.put(NO_OF_DAYS, std.no_of_days)
        contentValues.put(NO_OF_ADULTS, std.no_of_adults)
        contentValues.put(NO_OF_CHILDREN, std.no_of_children)
        contentValues.put(ROOM_TYPE, std.room_type)

        val success = db.insert(TABLE_BOOKING, null, contentValues)
        db.close()
        return success
    }

    //VIEW HOTELS BOOKINGS - IT21307362
    @SuppressLint("Range")
    fun getAllBookings(): ArrayList<BookingModel> {
        val stdList: ArrayList<BookingModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_BOOKING"
        val db = this.writableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var booking_date: String
        var no_of_days: Int
        var no_of_adults: Int
        var no_of_children: Int
        var room_type: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                booking_date = cursor.getString(cursor.getColumnIndex("booking_date"))
                no_of_days = cursor.getInt(cursor.getColumnIndex("no_of_days"))
                no_of_adults = cursor.getInt(cursor.getColumnIndex("no_of_adults"))
                no_of_children = cursor.getInt(cursor.getColumnIndex("no_of_children"))
                room_type = cursor.getString(cursor.getColumnIndex("room_type"))

                val std = BookingModel(
                    id = id, booking_date = booking_date, no_of_days = no_of_days,
                    no_of_adults = no_of_adults, no_of_children = no_of_children,
                    room_type = room_type
                )
                stdList.add(std)
            } while (cursor.moveToNext())
        }

        return stdList
    }

    //UPDATE HOTELS BOOKINGS - IT21307362
    fun updateBooking(std: BookingModel): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(BOOKING_DATE, std.booking_date)
        contentValues.put(NO_OF_DAYS, std.no_of_days)
        contentValues.put(NO_OF_ADULTS, std.no_of_adults)
        contentValues.put(NO_OF_CHILDREN, std.no_of_children)
        contentValues.put(ROOM_TYPE, std.room_type)

        val success = db.update(TABLE_BOOKING, contentValues, "id=" + std.id, null)
        db.close()
        return success
    }

    //DELETE HOTELS BOOKINGS - IT21307362
    fun deleteBookingById(id: Int): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val success = db.delete(TABLE_BOOKING, "id=$id", null)
        db.close()
        return success
    }

    //search hotel IT21307362


    //INSERT RESTURANT DATA - IT21198090
    fun insertuserdata(Date: String?, Restaurant: String?, time: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("Date", Date)
        contentValues.put("Restaurant", Restaurant)
        contentValues.put("time", time)
        return if (Date != null) { //additions
            val result = db.insert("Res_Schedules", null, contentValues)
            result != -1L
        } else {
            false
        }
    }

    //UPDATE RESTURANT DATA - IT21198090
    fun updateuserdata(Date: String, Restaurant: String?, time: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("Restaurant", Restaurant)
        contentValues.put("time", time)
        val cursor = db.rawQuery("select * from Res_Schedules where Date=?", arrayOf(Date))
        return if (cursor.count > 0) {
            val result =
                db.update("Res_Schedules", contentValues, "Date=?", arrayOf(Date))
                    .toLong()
            result != -1L
        } else {
            false
        }
    }

    //DELETE RESTURANT DATA - IT21198090

    fun deleteuserdata(Date: String): Boolean {
        val db = this.writableDatabase
        val cursor = db.rawQuery("select * from Res_Schedules where Date=?", arrayOf(Date))
        return if (cursor.count > 0) {
            val result = db.delete("Res_Schedules", "Date=?", arrayOf(Date)).toLong()
            result != -1L
        } else {
            false
        }
    }

    //GET RESTURANT DATA - IT21198090
    fun getdata(): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("select * from Res_Schedules", null)
    }

    fun getdate(): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("select Restaurant from Res_Schedules", null)
    }

}
