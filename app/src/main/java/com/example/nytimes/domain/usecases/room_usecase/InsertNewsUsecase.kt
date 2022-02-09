package com.example.nytimes.domain.usecases.room_usecase

import androidx.lifecycle.LiveData
import com.example.nytimes.data.local.NewsEntity
import com.example.nytimes.data.repository.Repository
import javax.inject.Inject

class InsertNewsUsecase @Inject constructor(private val repository: Repository) {

    suspend fun InsertAllNews(newsEntity: NewsEntity){
        repository.InsertData(newsEntity)
    }


    fun readNews(): LiveData<List<NewsEntity>> {
        return  repository.readData()
    }
}