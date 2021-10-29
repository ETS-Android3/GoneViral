package com.blackopalsolutions.goneviral.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import com.blackopalsolutions.goneviral.android.model.service.CardService
import com.blackopalsolutions.goneviral.android.model.service.ServiceObserver
import com.blackopalsolutions.goneviral.model.request.IdRequest
import com.blackopalsolutions.goneviral.model.response.GetCardResponse
import java.util.*
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity(), ServiceObserver<GetCardResponse> {

    var numOfCardsInHand = 0
    var cardService = CardService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawCardBtn = findViewById<Button>(R.id.draw_card_button)
        val viewHandBtn = findViewById<Button>(R.id.view_hand_button)

        drawCardBtn.setOnClickListener {
            numOfCardsInHand++;
            val id = nextInt(1, 35)
            var req = IdRequest(id)
            cardService.getCard(req, this)


        }

        viewHandBtn.setOnClickListener {
            val intent = Intent(this, ViewHandActivity::class.java);
            startActivity(intent)
        }
    }

    override fun onSuccess(response: GetCardResponse) {
        Toast.makeText(this@MainActivity, "You just drew a card!", Toast.LENGTH_LONG).show()
    }

    override fun onFailure(response: GetCardResponse) {
        Toast.makeText(this@MainActivity, "You just failed to draw a card!", Toast.LENGTH_LONG).show()
    }

    override fun handleException(exception: Exception) {
        Toast.makeText(this@MainActivity, "You just drew an exception!", Toast.LENGTH_LONG).show()
    }
}
