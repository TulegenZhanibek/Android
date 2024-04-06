package com.example.lab2.entity

import com.google.gson.annotations.SerializedName

data class Dogs(
    @SerializedName("name") val name: String,
    @SerializedName("image_link") val image_link: String,
    @SerializedName("shedding") val shedding: Int,
    @SerializedName("grooming") val grooming: Int,
    @SerializedName("drooling") val drooling: Int,
)

