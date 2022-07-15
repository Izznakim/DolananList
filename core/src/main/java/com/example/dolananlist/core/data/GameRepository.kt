package com.example.dolananlist.core.data

import com.example.dolananlist.core.data.source.remote.RemoteDataSource
import com.example.dolananlist.core.data.source.remote.retrofit.ApiResponse
import com.example.dolananlist.core.domain.model.Game
import com.example.dolananlist.core.domain.model.GameDetail
import com.example.dolananlist.core.domain.repository.IGameRepository
import com.example.dolananlist.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class GameRepository(
    private val remoteDataSource: RemoteDataSource
) : IGameRepository {

    override fun getGameList(): Flow<ApiResponse<List<Game>>> = flow {
        val gameList = DataMapper.mapResponseToDomainGameList(remoteDataSource.getGameList())
        emitAll(gameList)
    }

    override fun getGameDetail(id: Int): Flow<ApiResponse<GameDetail>> = flow {
        val gameDetail =
            DataMapper.mapResponseToDomainGameDetail(remoteDataSource.getGameDetail(id))
        emitAll(gameDetail)
    }
}