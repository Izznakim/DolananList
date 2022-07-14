package com.example.dolananlist.core.domain.repository

import com.example.dolananlist.core.data.source.remote.response.GameDetailResponse
import com.example.dolananlist.core.data.source.remote.response.ResultsItem
import com.example.dolananlist.core.data.source.remote.retrofit.ApiResponse
import com.example.dolananlist.core.domain.model.GameDetail
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getGameList(): Flow<ApiResponse<List<ResultsItem>>>

    fun getGameDetail(id:Int): Flow<ApiResponse<GameDetail>>
}