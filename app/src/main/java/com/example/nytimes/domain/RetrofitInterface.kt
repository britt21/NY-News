package com.example.nytimes.domain

import com.example.nytimes.data.remote.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("/svc/mostpopular/v2/emailed/7.json?")
   suspend fun getNYTimes(@Query ("api-key")apikey: String): Response<News>
}