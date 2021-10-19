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

        // TODO: display the actual hand, mocked data below
        for (i in 1..7) {
            data.add(CardsViewModel(R.drawable.back_to_school))
        }

        val adapter = CustomAdapter(data)
        handRecyclerView.adapter = adapter
    }
}