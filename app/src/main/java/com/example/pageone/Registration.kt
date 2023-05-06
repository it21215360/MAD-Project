package com.example.pageone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Registration : AppCompatActivity() {

    private lateinit var uname: EditText
    private lateinit var password: EditText
    private lateinit var email: EditText
    private lateinit var cpwd: EditText
    private lateinit var saveBtn: Button

    internal var dbHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        uname = findViewById<EditText>(R.id.getUsername)
        password = findViewById<EditText>(R.id.getPassword)
        email = findViewById<EditText>(R.id.getEmail)
        cpwd = findViewById<EditText>(R.id.confirmPass)
        saveBtn = findViewById<Button>(R.id.signUp)

        saveBtn.setOnClickListener {
            val unameTxt = uname.text.toString()
            val pwd = password.text.toString()
            val pass = cpwd.text.toString()
            val email = email.text.toString()
            val saveData = dbHelper.insertUserData(unameTxt,email,pwd)

            if(TextUtils.isEmpty(unameTxt) || TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pass)) {
                Toast.makeText(this,"Add Username, Email, Password & Confirm Password", Toast.LENGTH_SHORT).show()
            }else{
                if(pwd == pass){
                    if(saveData){
                        Toast.makeText(this,"Signup Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, Login::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"User Exists", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this,"Password is Not Matching", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}