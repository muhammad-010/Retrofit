package com.example.apiapplication.network

import com.example.apiapplication.model.users
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users?page=2")
    fun getAllUsers(): Call<users>
}