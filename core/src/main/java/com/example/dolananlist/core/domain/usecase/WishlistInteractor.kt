package com.example.dolananlist.core.domain.usecase

import com.example.dolananlist.core.data.source.remote.response.GameDetailResponse
import com.example.dolananlist.core.domain.model.GameDetail
import com.example.dolananlist.core.domain.model.Wishlist
import com.example.dolananlist.core.domain.repository.IWishlistRepository
import kotlinx.coroutines.flow.Flow

class WishlistInteractor(private val wishlistRepository: IWishlistRepository) : WishlistUseCase {
    override fun setGameWishlist(game: GameDetail) =
        wishlistRepository.setGameWishlist(game)

    override suspend fun checkExistOrNot(id: Int): Boolean = wishlistRepository.checkExistOrNot(id)

    override fun deleteGameFromWishlist(game: GameDetail) =
        wishlistRepository.deleteGameFromWishlist(game)

    override fun getWishlist(): Flow<List<Wishlist>> = wishlistRepository.getWishlist()
}