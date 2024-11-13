package com.example.apiapplication.model

import com.google.gson.annotations.SerializedName

data class users(
    @SerializedName("data")
    val `data`: List<data>
)
