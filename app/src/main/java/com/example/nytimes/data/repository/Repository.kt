package com.example.nytimes.data.repository

import androidx.lifecycle.LiveData
import com.example.nytimes.data.local.NewsEntity
import com.example.nytimes.data.remote.News
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response

@ActivityRetainedScoped
interface Repository {
   suspend fun getNYTime(): Response<News>

   suspend fun InsertData(newsEntity: NewsEntity)


   fun readData(): LiveData<List<NewsEntity>>
}