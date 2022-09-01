package com.example.quotes.Api

import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface ApiService {
    @GET("api/quotes")
    fun getQuote() : Call<List<Quote>>
}