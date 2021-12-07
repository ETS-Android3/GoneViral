package com.blackopalsolutions.goneviral.android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.blackopalsolutions.goneviral.model.request.IdRequest
import kotlin.random.Random

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var username = findViewById<EditText>(R.id.inputUsername).text
        var password = findViewById<EditText>(R.id.inputPassword).text

        val loginBtn = findViewById<Button>(R.id.btnLogin)
        val registerBtn = findViewById<Button>(R.id.btnCreateAccount)

        loginBtn.setOnClickListener {
            // add actual checking functionality
            val intent = Intent(this, MainActivity::class.java)
            // probably need to add username and/or other information to intent
            startActivity(intent)
        }

        registerBtn.setOnClickListener {
            // add actual registration functionality
            val intent = Intent(this, MainActivity::class.java)
            // probably need to add username and/or other information to intent
            startActivity(intent)
        }

    }
}