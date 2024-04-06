package com.example.lab2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dogs, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_dogs)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        dogsAdapter = DogsAdapter()
        recyclerView.adapter = dogsAdapter
        fetchDogsData() // Call method to fetch dogs data
        return view
    }

    private fun fetchDogsData() {
        ApiDogsClient.apiDogsService.getDogsByName("name").enqueue(object : Callback<List<Dogs>> {
            override fun onResponse(call: Call<List<Dogs>>, response: Response<List<Dogs>>) {
                if (response.isSuccessful) {
                    response.body()?.let { dogsList ->
                        dogsAdapter.submitList(dogsList)
                    }
                } else {
                    // Handle error response
                }
            }

            override fun onFailure(call: Call<List<Dogs>>, t: Throwable) {
                // Handle network failure
            }
        })
    }
}
