package com.example.nytimes.data.remote.di

import android.content.Context
import androidx.room.Room
import com.example.nytimes.data.local.NewsDao
import com.example.nytimes.data.local.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NewsDatabase {
        return Room.databaseBuilder(
            context, NewsDatabase::class.java, "newsDatabase"
        ).build()
    }

    @Singleton
    @Provides
    fun getDao(newsDatabase: NewsDatabase): NewsDao {
        return newsDatabase.newsdao
    }
}