package com.example.nytimes.domain

import com.example.nytimes.data.remote.News
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("/svc/mostpopular/v2/emailed/7.json?api-key=Su7yY0ZRR9Eyx2X7xHJri7CtFNLCaEvW")
   suspend fun getNYTimes(): Response<News>
}