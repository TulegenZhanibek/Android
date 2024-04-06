package com.example.dodopizza

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OrderActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        recyclerView = findViewById(R.id.relativeView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Получаем данные из Intent
        val items = intent.getSerializableExtra("items") as ArrayList<Pair<Int, Pair<String, String>>>?

        if (items != null) {
            adapter = OrderAdapter(items)
            recyclerView.adapter = adapter
        }
    }
}


