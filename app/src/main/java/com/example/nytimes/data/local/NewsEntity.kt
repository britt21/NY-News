package com.example.nytimes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nytimes.data.remote.News

@Entity
class NewsEntity(
    @PrimaryKey
    val id : Int,
    val data : News
)