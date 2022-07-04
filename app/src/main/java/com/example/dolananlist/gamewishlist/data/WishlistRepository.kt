package com.example.dolananlist.gamewishlist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import com.example.dolananlist.core.utils.AppExecutors
import com.example.dolananlist.core.utils.DataMapper
import com.example.dolananlist.gamewishlist.data.local.LocalDataSource
import com.example.dolananlist.gamewishlist.data.local.entity.WishlistEntity
import com.example.dolananlist.gamewishlist.domain.model.Wishlist
import com.example.dolananlist.gamewishlist.domain.repository.IWishlistRepository

class WishlistRepository private constructor(
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IWishlistRepository {
    override fun setGameWishlist(game: GameDetailResponse) {
        val wishlistEntity = DataMapper.mapResponsesToEntities(game)
        val wishlist = ArrayList<WishlistEntity>()
        wishlist.add(wishlistEntity)
        appExecutors.diskIO.execute {
            localDataSource.insertGameToWishlist(wishlist)
        }
    }

    override suspend fun checkExistOrNot(id: Int): Boolean {
        return localDataSource.checkExistOrNot(id)
    }

    override fun deleteGameFromWishlist(game: GameDetailResponse) {
        val wishlistEntity = DataMapper.mapResponsesToEntities(game)
        appExecutors.diskIO.execute {
            localDataSource.deleteGameFromWishlist(wishlistEntity)
        }
    }

    override fun getWishlist(): LiveData<List<Wishlist>> {
        return Transformations.map(localDataSource.getWishlist()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    companion object {
        @Volatile
        private var instance: WishlistRepository? = null
        fun getInstance(
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): WishlistRepository =
            instance ?: synchronized(this) {
                instance ?: WishlistRepository(localDataSource, appExecutors)
            }.also { instance = it }
    }
}