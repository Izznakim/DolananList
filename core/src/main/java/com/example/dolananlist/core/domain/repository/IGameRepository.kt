package com.example.dolananlist.core.domain.repository

import com.example.dolananlist.core.data.source.remote.retrofit.ApiResponse
import com.example.dolananlist.core.domain.model.Game
import com.example.dolananlist.core.domain.model.GameDetail
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getGameList(): Flow<ApiResponse<List<Game>>>

    fun getGameDetail(id:Int): Flow<ApiResponse<GameDetail>>
}