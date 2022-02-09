package com.example.nytimes.domain.usecases.netwok_usecase

import com.example.nytimes.data.remote.News
import com.example.nytimes.data.repository.Repository
import retrofit2.Response
import javax.inject.Inject

class GetNewsNetworkUseCase @Inject constructor(private val repository: Repository) {
   suspend fun getTime(): Response<News> {
        return repository.getNYTime()
    }
}