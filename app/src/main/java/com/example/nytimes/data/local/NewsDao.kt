package com.example.nytimes.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(nyEntity: NewsEntity)

    @Query("Select * from NewsEntity")
    fun ReadNewsData(): LiveData<List<NewsEntity>>


}