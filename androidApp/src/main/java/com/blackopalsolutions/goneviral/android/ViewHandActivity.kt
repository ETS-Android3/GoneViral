package com.blackopalsolutions.goneviral.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

class ViewHandActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_hand)

        val handRecyclerView = findViewById<RecyclerView>(R.id.hand_recycler_view)
        handRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val data = ArrayList<CardsViewModel>()
        val cardSlugs = intent.getStringArrayExtra("cardsInHand")

        println(cardSlugs?.joinToString())

        if (cardSlugs != null) {
            for (i in cardSlugs) {
                val id = this.applicationContext.resources.getIdentifier(i, "drawable", this.applicationContext.packageName)
                data.add(CardsViewModel(id))
            }
        }

        val adapter = CustomAdapter(data)
        handRecyclerView.adapter = adapter
    }
}