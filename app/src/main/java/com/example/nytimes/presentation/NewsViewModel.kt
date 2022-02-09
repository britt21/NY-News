package com.example.nytimes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimes.data.local.NewsEntity
import com.example.nytimes.data.remote.News
import com.example.nytimes.domain.NetworkResult
import com.example.nytimes.domain.usecases.netwok_usecase.GetNewsNetworkUseCase
import com.example.nytimes.domain.usecases.room_usecase.InsertNewsUsecase
import com.example.nytimes.domain.usecases.room_usecase.ReadDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val readDatabaaseuseCase : ReadDatabaseUseCase,
    private val getNYTimeUseCase: GetNewsNetworkUseCase,
    private val insertNewsUsecase: InsertNewsUsecase
) : ViewModel() {

    private val _getNYTime = MutableLiveData<NetworkResult<News>>()
    val getNyTime : LiveData<NetworkResult<News>>
    get() = _getNYTime


    val readDatabaseUseCase : LiveData<List<NewsEntity>> = readDatabaaseuseCase.readData()

 fun insert(entity: NewsEntity){
        viewModelScope.launch {
            insertNewsUsecase.InsertAllNews(entity)
        }

    }
    fun getTime(){
        viewModelScope.launch {
            getTimeSafeCall()
        }
    }

    private  suspend fun getTimeSafeCall() {

        try {
            val response = getNYTimeUseCase.getTime()
            _getNYTime.value = handleTimeResponse(response)
            val result = _getNYTime.value
            if (result != null){
                offlinecache(result)
            }
        }catch (e: Exception){

        }
    }

    private suspend  fun offlinecache(result: NetworkResult<News>) {
        val newsEntity = NewsEntity(0, result.data!!)
        insert(
            newsEntity)
    }

    private fun handleTimeResponse(response: Response<News>): NetworkResult<News>? {

        when{
            //input all response by error code eg : {200}, {402}, {502}...and so on
            response.isSuccessful ->{
                val result = response.body()
             return NetworkResult.Sucess(result!!)
            }
            else ->{
             return NetworkResult.Error("Error 404")
                }
            }


        }
    }