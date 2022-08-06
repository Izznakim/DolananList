package com.example.dolananlist.core.data.source.remote

import com.example.dolananlist.core.data.source.remote.response.GameDetailResponse
import com.example.dolananlist.core.data.source.remote.response.ResultsItem
import com.example.dolananlist.core.data.source.remote.retrofit.ApiResponse
import com.example.dolananlist.core.data.source.remote.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val apiService: ApiService) {
    fun getGameList(): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            try {
                val response = apiService.getGameList("380488d9a4a4466c9a89fa586b0617ab")
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getGameDetail(id: Int): Flow<ApiResponse<GameDetailResponse>> {
        return flow {
            try {
                val response = apiService.getGameDetail(id, "380488d9a4a4466c9a89fa586b0617ab")
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}