package com.example.quotes.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object ApiConfig {

    const val BASE_URL = "https://type.fit/"

    fun getRetrofit() : Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getService() : ApiService{
        return getRetrofit().create()
    }
}