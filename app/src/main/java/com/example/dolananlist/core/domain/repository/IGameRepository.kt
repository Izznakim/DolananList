package com.example.dolananlist.core.domain.repository

import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import com.example.dolananlist.core.data.remote.response.ResultsItem
import com.example.dolananlist.core.data.remote.retrofit.ApiResponse
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getGameList(): Flow<ApiResponse<List<ResultsItem>>>

    fun getGameDetail(id:Int): Flow<ApiResponse<GameDetailResponse>>
}