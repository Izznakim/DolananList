package com.example.dolananlist.gamewishlist.data

import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import com.example.dolananlist.core.utils.AppExecutors
import com.example.dolananlist.core.utils.DataMapper
import com.example.dolananlist.gamewishlist.data.local.LocalDataSource
import com.example.dolananlist.gamewishlist.data.local.entity.WishlistEntity
import com.example.dolananlist.gamewishlist.domain.model.Wishlist
import com.example.dolananlist.gamewishlist.domain.repository.IWishlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WishlistRepository (
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

    override fun getWishlist(): Flow<List<Wishlist>> {
        return localDataSource.getWishlist().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    companion object
}