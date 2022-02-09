package com.example.nytimes.data.remote

import com.google.gson.annotations.SerializedName

data class News(

    @SerializedName("results")
    val results: List<Result>,

    )