package com.example.dolananlist.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.dolananlist.core.data.remote.RemoteDataSource
import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import com.example.dolananlist.core.data.remote.response.ResultsItem
import com.example.dolananlist.core.data.remote.retrofit.ApiResponse
import com.example.dolananlist.core.domain.repository.IGameRepository
import com.example.dolananlist.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class GameRepository private constructor(
    private val remoteDataSource: RemoteDataSource
):IGameRepository {

    override fun getGameList(): Flow<ApiResponse<List<ResultsItem>>> = flow {
        val game=remoteDataSource.getGameList()
        emitAll(game)
    }

    override fun getGameDetail(id: Int): Flow<ApiResponse<GameDetailResponse>> = flow{
        val detailGame=remoteDataSource.getGameDetail(id)
        emitAll(detailGame)
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