package com.example.lab2.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.ApiDogsClient
import com.example.lab2.R
import com.example.lab2.adapter.DogsAdapter
import com.example.lab2.entity.Dogs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DogsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dogsAdapter: DogsAdapter
    private var fullDogList: List<Dogs> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dogs, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_dogs)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        dogsAdapter = DogsAdapter()
        recyclerView.adapter = dogsAdapter

        val searchEditText = view.findViewById<EditText>(R.id.searchEditText)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val searchText = s.toString().trim()
                filterDogs(searchText)
            }
        })

        searchEditText.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_UP && searchEditText.text.toString().isEmpty()) {
                resetSearch()
                return@OnKeyListener true
            }
            false
        })

        fetchDogsData()

        return view
    }

    private fun fetchDogsData() {
        ApiDogsClient.apiDogsService.getDogsByName("retriever").enqueue(object : Callback<List<Dogs>> {
            override fun onResponse(call: Call<List<Dogs>>, response: Response<List<Dogs>>) {
                if (response.isSuccessful) {
                    response.body()?.let { dogsList ->
                        fullDogList = dogsList // Сохраняем полный список данных
                        dogsAdapter.submitList(dogsList) // Передаем полный список данных в адаптер
                    }
                } else {
                    // Обработка ошибки
                }
            }

            override fun onFailure(call: Call<List<Dogs>>, t: Throwable) {
                // Обработка ошибки сети
            }
        })
    }

    private fun filterDogs(query: String) {
        val filteredList = if (query.isBlank()) {
            fullDogList // Если запрос пустой, возвращаем полный список
        } else {
            fullDogList.filter { it.name.contains(query, ignoreCase = true) }
        }
        dogsAdapter.submitList(filteredList) // Передаем отфильтрованный список в адаптер
    }

    private fun resetSearch() {
        fetchDogsData() // Повторно загружаем данные из API
    }
}

