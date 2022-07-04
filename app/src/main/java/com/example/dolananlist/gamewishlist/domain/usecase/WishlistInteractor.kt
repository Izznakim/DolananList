package com.example.dolananlist.gamewishlist.domain.usecase

import androidx.lifecycle.LiveData
import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import com.example.dolananlist.gamewishlist.domain.model.Wishlist
import com.example.dolananlist.gamewishlist.domain.repository.IWishlistRepository

class WishlistInteractor(private val wishlistRepository: IWishlistRepository) : WishlistUseCase {
    override fun setGameWishlist(game: GameDetailResponse) =
        wishlistRepository.setGameWishlist(game)

    override suspend fun checkExistOrNot(id: Int): Boolean = wishlistRepository.checkExistOrNot(id)

    override fun deleteGameFromWishlist(game: GameDetailResponse) =
        wishlistRepository.deleteGameFromWishlist(game)

    override fun getWishlist(): LiveData<List<Wishlist>> = wishlistRepository.getWishlist()
}