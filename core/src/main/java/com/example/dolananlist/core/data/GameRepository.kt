package com.example.dolananlist.core.data

import com.example.dolananlist.core.data.remote.RemoteDataSource
import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import com.example.dolananlist.core.data.remote.response.ResultsItem
import com.example.dolananlist.core.data.remote.retrofit.ApiResponse
import com.example.dolananlist.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class GameRepository(
    private val remoteDataSource: RemoteDataSource
): IGameRepository {

    override fun getGameList(): Flow<ApiResponse<List<ResultsItem>>> = flow {
        val game=remoteDataSource.getGameList()
        emitAll(game)
    }

    override fun getGameDetail(id: Int): Flow<ApiResponse<GameDetailResponse>> = flow{
        val detailGame=remoteDataSource.getGameDetail(id)
        emitAll(detailGame)
    }

    companion object
}