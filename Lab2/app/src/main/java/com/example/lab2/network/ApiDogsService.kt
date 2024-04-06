package com.example.lab2.network

import com.example.lab2.entity.Dogs
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiDogsService {
    @GET("/v1/dogs")
    fun getDogsByName(@Query("name") name: String): Call<List<Dogs>>
}
