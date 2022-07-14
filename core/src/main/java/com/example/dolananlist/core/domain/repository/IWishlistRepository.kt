package com.example.dolananlist.core.domain.repository

import com.example.dolananlist.core.data.source.remote.response.GameDetailResponse
import com.example.dolananlist.core.domain.model.GameDetail
import com.example.dolananlist.core.domain.model.Wishlist
import kotlinx.coroutines.flow.Flow

interface IWishlistRepository {
    fun setGameWishlist(game: GameDetail)

    suspend fun checkExistOrNot(id: Int): Boolean

    fun deleteGameFromWishlist(game: GameDetail)

    fun getWishlist(): Flow<List<Wishlist>>
}