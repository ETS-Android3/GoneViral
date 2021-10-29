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

    private var cardsInHand: MutableList<String> = ArrayList()
    var cardDeck = listOf("back_to_school", "baking", "caligraphy", "cat_bath", "ic_dance_party_for_one")

    var cardService = CardService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawCardBtn = findViewById<Button>(R.id.draw_card_button)
        val viewHandBtn = findViewById<Button>(R.id.view_hand_button)

        drawCardBtn.setOnClickListener {
            cardsInHand.add(cardDeck[cardsInHand.size])
            val id = nextInt(1, 35)
            val req = IdRequest(id)
//            cardService.getCard(req, this)
        }

        viewHandBtn.setOnClickListener {
            val intent = Intent(this, ViewHandActivity::class.java)
            intent.putExtra("cardsInHand", cardsInHand.toTypedArray())
            startActivity(intent)
        }
    }

    override fun onSuccess(response: GetCardResponse) {
        val cardTitle = response.card?.title
        Toast.makeText(this@MainActivity, "You just drew a card! Title: $cardTitle", Toast.LENGTH_LONG).show()
    }

    override fun onFailure(response: GetCardResponse) {
        Toast.makeText(this@MainActivity, response.message, Toast.LENGTH_LONG).show()
    }

    override fun handleException(exception: Exception) {
        Toast.makeText(this@MainActivity, "There was an exception!", Toast.LENGTH_LONG).show()
        println("**************************************************")
        println(exception.message)
        println(exception.stackTrace.joinToString(",\n"))
    }
}
