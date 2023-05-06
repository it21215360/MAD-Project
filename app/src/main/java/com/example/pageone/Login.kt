package com.example.pageone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {

    private lateinit var userName: EditText
    private lateinit var pass: EditText
    private lateinit var loginBtn: Button
    private lateinit var signUpBtn: Button

    private var dbHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userName = findViewById<EditText>(R.id.userName)
        pass =findViewById<EditText>(R.id.userPassword)
        loginBtn = findViewById<Button>(R.id.successBtn)
        signUpBtn = findViewById<Button>(R.id.signUpPageBtn)

        loginBtn.setOnClickListener{
            val usernameTxt = userName.text.toString()
            val passText = pass.text.toString()

            if(TextUtils.isEmpty(usernameTxt) || TextUtils.isEmpty(passText)) {
                Toast.makeText(this, "Add Username or Password", Toast.LENGTH_SHORT).show()
            }
            else{
                val checkUser = dbHelper.checkPass(usernameTxt,passText)
                if(checkUser){
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, MainSelection::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Wrong Username or Password", Toast.LENGTH_SHORT).show()
                }

            }

        }

        signUpBtn.setOnClickListener{
            val intent = Intent(this,Registration::class.java)
            startActivity(intent)
        }


    }
}