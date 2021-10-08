package com.blackopalsolutions.goneviral.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewHandBtn = findViewById<Button>(R.id.view_hand_button)
        viewHandBtn.setOnClickListener {
            Toast.makeText(this@MainActivity, "TODO: show hand", Toast.LENGTH_SHORT).show()
        }
    }
}
