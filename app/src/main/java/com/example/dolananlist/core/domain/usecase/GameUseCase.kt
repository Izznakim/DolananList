package com.example.dolananlist.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import com.example.dolananlist.core.data.remote.response.ResultsItem
import com.example.dolananlist.core.data.remote.retrofit.ApiResponse

interface GameUseCase {
    fun getGameList():LiveData<ApiResponse<List<ResultsItem>>>
    fun getGameDetail(id:Int):LiveData<ApiResponse<GameDetailResponse>>
}