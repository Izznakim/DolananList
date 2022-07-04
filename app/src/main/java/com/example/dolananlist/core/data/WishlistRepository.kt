package com.example.dolananlist.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.example.dolananlist.core.data.local.entity.WishlistEntity
import com.example.dolananlist.core.data.local.room.WishlistDao
import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import com.example.dolananlist.core.utils.AppExecutors
import com.example.dolananlist.core.utils.DataMapper
import com.example.dolananlist.gamewishlist.domain.model.Wishlist
import com.example.dolananlist.gamewishlist.domain.repository.IWishlistRepository

class WishlistRepository private constructor(
    private val wishlistDao: WishlistDao,
    private val appExecutors: AppExecutors
):IWishlistRepository {
    override fun setGameWishlist(game: GameDetailResponse) {
        val wishlistEntity=DataMapper.mapResponsesToEntities(game)
        val wishlist = ArrayList<WishlistEntity>()
        wishlist.add(wishlistEntity)
        appExecutors.diskIO.execute {
            wishlistDao.insertGameToWishlist(wishlist)
        }
    }

    override suspend fun checkExistOrNot(id: Int): Boolean {
        return wishlistDao.checkExistOrNot(id)
    }

    override fun deleteGameFromWishlist(game: GameDetailResponse) {
        val wishlistEntity=DataMapper.mapResponsesToEntities(game)
        appExecutors.diskIO.execute {
            wishlistDao.deleteGameFromWishlist(wishlistEntity)
        }
    }

    override fun getWishlist(): LiveData<List<Wishlist>> {
        return Transformations.map(wishlistDao.getWishlist()){
            DataMapper.mapEntitiesToDomain(it)
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