package com.example.dolananlist.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.dolananlist.core.data.remote.RemoteDataSource
import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import com.example.dolananlist.core.data.remote.response.ResultsItem
import com.example.dolananlist.core.data.remote.retrofit.ApiResponse
import com.example.dolananlist.core.domain.repository.IGameRepository
import com.example.dolananlist.core.utils.AppExecutors

class GameRepository private constructor(
    private val remoteDataSource: RemoteDataSource
):IGameRepository {
    private val resultList=MediatorLiveData<ApiResponse<List<ResultsItem>>>()
    private val result=MediatorLiveData<ApiResponse<GameDetailResponse>>()

    override fun getGameList(): LiveData<ApiResponse<List<ResultsItem>>> {
        val game=remoteDataSource.getGameList()
        resultList.addSource(game){newData ->
            resultList.value=newData
        }
        return resultList
    }

    override fun getGameDetail(id: Int): LiveData<ApiResponse<GameDetailResponse>> {
        val detailGame=remoteDataSource.getGameDetail(id)
        result.addSource(detailGame){newData->
            result.value=newData
        }
        return result
    }

    companion object {
        @Volatile
        private var instance: GameRepository? = null
        fun getInstance(
            remoteDataSource: RemoteDataSource
        ): GameRepository =
            instance ?: synchronized(this) {
                instance ?: GameRepository(remoteDataSource)
            }.also { instance = it }
    }
}