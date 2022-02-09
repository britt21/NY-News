package com.example.nytimes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nytimes.domain.DataConverter

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsdao : NewsDao
}