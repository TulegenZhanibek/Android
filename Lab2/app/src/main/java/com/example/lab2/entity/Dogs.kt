package com.example.lab2.entity

import com.google.gson.annotations.SerializedName

data class Dogs(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image_link") val image_link: String
)

