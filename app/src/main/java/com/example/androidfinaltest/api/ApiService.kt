package com.example.androidfinaltest.api

import com.example.androidfinaltest.Posts
import com.example.androidfinaltest.Products
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("products")
    fun getItems(): Call<List<Products>>
}