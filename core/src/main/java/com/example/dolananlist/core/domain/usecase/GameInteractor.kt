package com.example.dolananlist.core.domain.usecase

import com.example.dolananlist.core.data.source.remote.retrofit.ApiResponse
import com.example.dolananlist.core.domain.model.Game
import com.example.dolananlist.core.domain.model.GameDetail
import com.example.dolananlist.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository) : GameUseCase {
    override fun getGameList(): Flow<ApiResponse<List<Game>>> = gameRepository.getGameList()

    override fun getGameDetail(id: Int): Flow<ApiResponse<GameDetail>> =
        gameRepository.getGameDetail(id)
}