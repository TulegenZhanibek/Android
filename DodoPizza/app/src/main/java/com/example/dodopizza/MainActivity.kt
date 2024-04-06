package com.example.dodopizza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class MainActivity : AppCompatActivity(), CustomAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var adapter: CustomAdapter

    private val dataArray = arrayOf(
        CustomAdapter.MenuItem(R.drawable.pizzakebab, "Wow! Kebab", "Beef kebab, ranch sauce, cheddar cheese, sweet pepper, tomatoes, red onion, marinara sauce", "from 2,900 KZT"),
        CustomAdapter.MenuItem(R.drawable.pizzapepperoni, "Pepperoni with mushrooms", "Chicken pepperoni, mozzarella cheese, mushrooms, Alfredo sauce", "from 2,000 KZT"),
        CustomAdapter.MenuItem(R.drawable.pizzapickles, "Ham and Pickles", "Ranch sauce, mozzarella cheese chicken ham, pickles, red onion", "from 2,000 KZT"),
        CustomAdapter.MenuItem(R.drawable.pizzamix, "Pizza mix", "Four different quarters with chicken ham, tomatoes, bryndza cheese mozzarella, Alfredo sauce", "2,200 KZT"),
        CustomAdapter.MenuItem(R.drawable.pizzajulienne, "Julienne", "Chicken, mushrooms, rich mushroom sauce, red onion, garlic, mozzarella cheese, cheddar cheese, parmesan cheese, Alfredo sauce", "from 2,700 KZT"),
        CustomAdapter.MenuItem(R.drawable.pizzacheesy, "Cheesy", "Mozzarella cheese, cheddar cheese parmesan cheese, Alfredo sauce", "from 1,900 KZT"),
        CustomAdapter.MenuItem(R.drawable.pizzapepperonifresh, "Pepperoni Fresh", "Chicken pepperoni, extra mozzarella cheese, tomatoes, marinara sauce", "from 2,000 KZT"),
        CustomAdapter.MenuItem(R.drawable.pizzadoublechicken, "Double Chicken", "Double chicken, mozzarella cheese, Alfredo sauce", "from 2,100 KZT"),
        CustomAdapter.MenuItem(R.drawable.pizzachorizofresh, "Chorizo fresh", "Spicy chorizo, sweet pepper, mozzarella cheese, marinara sauce", "from 1,900 KZT")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchEditText = findViewById(R.id.searchEditText)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CustomAdapter(this, dataArray)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(this)

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
        })
    }

    private fun filter(text: String) {
        val filteredArray = dataArray.filter {
            it.name.contains(text, ignoreCase = true)
        }.toTypedArray()

        adapter.setData(filteredArray)
    }

    override fun onItemClick(item: CustomAdapter.MenuItem) {
        val intent = Intent(this, OrderActivity::class.java)
        intent.putExtra("imageResource", item.imageResource)
        intent.putExtra("name", item.name)
        intent.putExtra("description", item.description)
        intent.putExtra("cost", item.cost)
        startActivity(intent)
    }
}
