package com.example.dolananlist.core.data.source.local

import com.example.dolananlist.core.data.source.local.entity.WishlistEntity
import com.example.dolananlist.core.data.source.local.room.WishlistDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource (private val wishlistDao: WishlistDao) {
    fun insertGameToWishlist(wishlist: List<WishlistEntity>) =
        wishlistDao.insertGameToWishlist(wishlist)

    fun getWishlist(): Flow<List<WishlistEntity>> = wishlistDao.getWishlist()

    suspend fun checkExistOrNot(id: Int): Boolean = wishlistDao.checkExistOrNot(id)

    fun deleteGameFromWishlist(wishlist: WishlistEntity) =
        wishlistDao.deleteGameFromWishlist(wishlist)
}