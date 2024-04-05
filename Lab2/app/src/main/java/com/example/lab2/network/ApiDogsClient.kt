package com.example.lab2

import com.example.lab2.network.ApiDogsService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiDogsClient {

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(Interceptor { chain ->
            val request = chain.request().newBuilder()
                .header("X-Api-Key", "uG71SzNeOK0YSLCytdAZvg==N6IE2jQgL8rKsb1w")
                .build()
            chain.proceed(request)
        })
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.api-ninjas.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val apiDogsService: ApiDogsService by lazy {
        retrofit.create(ApiDogsService::class.java)
    }
}
