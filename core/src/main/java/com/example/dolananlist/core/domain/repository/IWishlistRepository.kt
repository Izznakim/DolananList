package com.example.dolananlist.core.domain.repository

import com.example.dolananlist.core.domain.model.GameDetail
import com.example.dolananlist.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IWishlistRepository {
    fun setGameWishlist(game: GameDetail)

    suspend fun checkExistOrNot(id: Int): Boolean

    fun deleteGameFromWishlist(game: GameDetail)

    fun getWishlist(): Flow<List<Game>>
}