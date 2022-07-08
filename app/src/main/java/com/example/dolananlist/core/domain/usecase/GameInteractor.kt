package com.example.dolananlist.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import com.example.dolananlist.core.data.remote.response.ResultsItem
import com.example.dolananlist.core.data.remote.retrofit.ApiResponse
import com.example.dolananlist.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository) : GameUseCase {
    override fun getGameList(): Flow<ApiResponse<List<ResultsItem>>> = gameRepository.getGameList()

    override fun getGameDetail(id: Int): Flow<ApiResponse<GameDetailResponse>> =
        gameRepository.getGameDetail(id)
}