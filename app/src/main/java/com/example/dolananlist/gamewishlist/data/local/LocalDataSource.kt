package com.example.dolananlist.gamewishlist.data.local

import androidx.lifecycle.LiveData
import com.example.dolananlist.gamewishlist.data.local.entity.WishlistEntity
import com.example.dolananlist.gamewishlist.data.local.room.WishlistDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource (private val wishlistDao: WishlistDao) {
    fun insertGameToWishlist(wishlist: List<WishlistEntity>) =
        wishlistDao.insertGameToWishlist(wishlist)

    fun getWishlist(): Flow<List<WishlistEntity>> = wishlistDao.getWishlist()

    suspend fun checkExistOrNot(id: Int): Boolean = wishlistDao.checkExistOrNot(id)

    fun deleteGameFromWishlist(wishlist: WishlistEntity) =
        wishlistDao.deleteGameFromWishlist(wishlist)

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(wishlistDao: WishlistDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(wishlistDao)
            }
    }
}