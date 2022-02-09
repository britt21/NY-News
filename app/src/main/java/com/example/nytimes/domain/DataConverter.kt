package com.example.nytimes.domain

import androidx.room.TypeConverter
import com.example.nytimes.data.remote.News
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


var gson = Gson()


class DataConverter {


    var gson = Gson()

    @TypeConverter
    fun foodRecipeToString(newsList: News): String{
        return gson.toJson(newsList)
    }

    @TypeConverter
    fun stringToJson(data: String): News {
        val listType = object : TypeToken<News>(){}.type
        return gson.fromJson(data, listType)
    }




}