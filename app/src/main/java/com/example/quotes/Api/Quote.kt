package com.example.quotes.Api

import java.util.*
import com.google.gson.annotations.SerializedName

data class Quote (
    @SerializedName("text")
    val text: String?,
    @SerializedName("author")
    val author: String?
    )
