package com.example.dolananlist.gamewishlist.domain.usecase

import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import com.example.dolananlist.gamewishlist.domain.model.Wishlist
import kotlinx.coroutines.flow.Flow

interface WishlistUseCase {
    fun setGameWishlist(game: GameDetailResponse)
    suspend fun checkExistOrNot(id: Int): Boolean
    fun deleteGameFromWishlist(game: GameDetailResponse)
    fun getWishlist(): Flow<List<Wishlist>>
}