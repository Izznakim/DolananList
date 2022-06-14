package com.example.dolananlist.data

import com.example.dolananlist.data.local.entity.WishlistEntity
import com.example.dolananlist.data.local.room.WishlistDao
import com.example.dolananlist.data.remote.response.GameDetailResponse
import com.example.dolananlist.utils.AppExecutors

class WishlistRepository private constructor(
    private val wishlistDao: WishlistDao,
    private val appExecutors: AppExecutors
) {
    fun setGameWishlist(game: GameDetailResponse) {
        appExecutors.diskIO.execute {
            val wish =
                WishlistEntity(
                    game.id,
                    game.backgroundImage,
                    game.genres.joinToString { it.name },
                    game.name,
                    game.platforms.joinToString { it.platform.name })
            val wishlist = ArrayList<WishlistEntity>()
            wishlist.add(wish)
            wishlistDao.insertGameToWishlist(wishlist)
        }
    }

    suspend fun checkExistOrNot(id: Int): Boolean {
        return wishlistDao.checkExistOrNot(id)
    }

    fun deleteGameFromWishlist(game: GameDetailResponse) {
        appExecutors.diskIO.execute {
            val wish =
                WishlistEntity(
                    game.id,
                    game.backgroundImage,
                    game.genres.joinToString { it.name },
                    game.name,
                    game.platforms.joinToString { it.platform.name })
            wishlistDao.deleteGameFromWishlist(wish)
        }
    }

    companion object {
        @Volatile
        private var instance: WishlistRepository? = null
        fun getInstance(wishlistDao: WishlistDao, appExecutors: AppExecutors): WishlistRepository =
            instance ?: synchronized(this) {
                instance ?: WishlistRepository(wishlistDao, appExecutors)
            }.also { instance = it }
    }
}