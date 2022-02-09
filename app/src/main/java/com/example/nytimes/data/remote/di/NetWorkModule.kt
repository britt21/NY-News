package com.example.nytimes.data.remote.di

import com.example.nytimes.data.repository.Repository
import com.example.nytimes.data.repository.RepositoryImpl
import com.example.nytimes.domain.RetrofitInterface
import com.example.nytimes.common.Constants
import com.example.nytimes.data.local.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    @Singleton
    @Provides
    fun provideGson(): GsonConverterFactory {
    return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun getRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getRetofit(retrofit: Retrofit): RetrofitInterface {
        return retrofit.create(RetrofitInterface::class.java)
    }

    @Singleton
    @Provides
    fun getRepository(retrofitInterface: RetrofitInterface, newsDao: NewsDao): Repository {
        return RepositoryImpl(retrofitInterface, newsDao)

    }
}