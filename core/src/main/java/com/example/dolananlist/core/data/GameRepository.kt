package com.example.dolananlist.core.data

import com.example.dolananlist.core.data.source.remote.RemoteDataSource
import com.example.dolananlist.core.data.source.remote.response.ResultsItem
import com.example.dolananlist.core.data.source.remote.retrofit.ApiResponse
import com.example.dolananlist.core.data.source.remote.retrofit.ApiService
import com.example.dolananlist.core.domain.model.GameDetail
import com.example.dolananlist.core.domain.repository.IGameRepository
import com.example.dolananlist.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val apiService: ApiService,
    private val remoteDataSource: RemoteDataSource
) : IGameRepository {

    override fun getGameList(): Flow<ApiResponse<List<ResultsItem>>> = flow {
        val gameList = remoteDataSource.getGameList()
        emitAll(gameList)
    }

    override fun getGameDetail(id: Int): Flow<ApiResponse<GameDetail>> = flow {
//        remoteDataSource.getGameDetail(id).map {
//            when (it) {
//                is ApiResponse.Success -> {
//                    DataMapper.mapResponseToDomain(it.data)
//                }
//                is ApiResponse.Empty -> ApiResponse.Empty
//                is ApiResponse.Error -> ApiResponse.Error(it.errorMessage)
//                is ApiResponse.Loading -> ApiResponse.Loading
//            }
//        }
        val gameDetail=DataMapper.mapResponseToDomain(apiService,remoteDataSource.getGameDetail(id),id)
        emitAll(gameDetail)
    }
}