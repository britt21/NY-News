package com.example.nytimes.domain.usecases.room_usecase

import androidx.lifecycle.LiveData
import com.example.nytimes.data.local.NewsEntity
import com.example.nytimes.data.repository.Repository
import javax.inject.Inject

class ReadDatabaseUseCase @Inject constructor(private val repository: Repository) {

    fun readData(): LiveData<List<NewsEntity>> {
        return repository.readData()
    }
}