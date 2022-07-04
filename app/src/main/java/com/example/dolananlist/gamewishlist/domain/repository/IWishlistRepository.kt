package com.example.dolananlist.gamewishlist.domain.repository

import androidx.lifecycle.LiveData
import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import com.example.dolananlist.gamewishlist.domain.model.Wishlist

interface IWishlistRepository {
    fun setGameWishlist(game: GameDetailResponse)

    suspend fun checkExistOrNot(id: Int): Boolean

    fun deleteGameFromWishlist(game: GameDetailResponse)

    fun getWishlist(): LiveData<List<Wishlist>>
}