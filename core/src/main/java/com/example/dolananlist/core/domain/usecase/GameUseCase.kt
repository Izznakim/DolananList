package com.example.dolananlist.core.domain.usecase

import com.example.dolananlist.core.data.source.remote.response.GameDetailResponse
import com.example.dolananlist.core.data.source.remote.response.ResultsItem
import com.example.dolananlist.core.data.source.remote.retrofit.ApiResponse
import com.example.dolananlist.core.domain.model.GameDetail
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getGameList():Flow<ApiResponse<List<ResultsItem>>>
    fun getGameDetail(id:Int):Flow<ApiResponse<GameDetail>>
}