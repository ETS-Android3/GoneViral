package com.blackopalsolutions.goneviral.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var numOfCardsInHand = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawCardBtn = findViewById<Button>(R.id.draw_card_button)
        val viewHandBtn = findViewById<Button>(R.id.view_hand_button)

        drawCardBtn.setOnClickListener {
            numOfCardsInHand++;
            Toast.makeText(this@MainActivity, "You just drew a card!", Toast.LENGTH_LONG).show()
        }

        viewHandBtn.setOnClickListener {
            Toast.makeText(this@MainActivity, "# of cards in hand: $numOfCardsInHand", Toast.LENGTH_LONG).show()
        }
    }
}
