package com.example.nytimes.data.repository

import androidx.lifecycle.LiveData
import com.example.nytimes.data.local.NewsDao
import com.example.nytimes.data.local.NewsEntity
import com.example.nytimes.data.remote.News
import com.example.nytimes.domain.RetrofitInterface
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private  val retrofitInterface: RetrofitInterface, private val newsdao: NewsDao):
    Repository {
    override suspend fun getNYTime(apikey : String): Response<News> {
        return retrofitInterface.getNYTimes(apikey)
    }

    override suspend fun InsertData(newsEntity: NewsEntity) {
        newsdao.insertData(newsEntity)


    }

    override fun readData(): LiveData<List<NewsEntity>> {
       return newsdao.ReadNewsData()
    }
}