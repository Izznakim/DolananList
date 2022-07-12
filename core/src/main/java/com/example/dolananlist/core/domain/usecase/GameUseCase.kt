package com.example.dolananlist.core.domain.usecase

import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import com.example.dolananlist.core.data.remote.response.ResultsItem
import com.example.dolananlist.core.data.remote.retrofit.ApiResponse
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getGameList():Flow<ApiResponse<List<ResultsItem>>>
    fun getGameDetail(id:Int):Flow<ApiResponse<GameDetailResponse>>
}