package com.example.dolananlist.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import com.example.dolananlist.core.data.remote.response.ResultsItem
import com.example.dolananlist.core.data.remote.retrofit.ApiResponse

interface IGameRepository {
    fun getGameList(): LiveData<ApiResponse<List<ResultsItem>>>

    fun getGameDetail(id:Int): LiveData<ApiResponse<GameDetailResponse>>
}