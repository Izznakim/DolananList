package com.example.dolananlist.core.data

import com.example.dolananlist.core.data.source.local.LocalDataSource
import com.example.dolananlist.core.data.source.local.entity.WishlistEntity
import com.example.dolananlist.core.domain.model.GameDetail
import com.example.dolananlist.core.domain.model.Game
import com.example.dolananlist.core.domain.repository.IWishlistRepository
import com.example.dolananlist.core.utils.AppExecutors
import com.example.dolananlist.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WishlistRepository(
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IWishlistRepository {
    override fun setGameWishlist(game: GameDetail) {
        val wishlistEntity = DataMapper.mapDomainToEntities(game)
        val wishlist = ArrayList<WishlistEntity>()
        wishlist.add(wishlistEntity)
        appExecutors.diskIO.execute {
            localDataSource.insertGameToWishlist(wishlist)
        }
    }

    override suspend fun checkExistOrNot(id: Int): Boolean {
        return localDataSource.checkExistOrNot(id)
    }

    override fun deleteGameFromWishlist(game: GameDetail) {
        val wishlistEntity = DataMapper.mapDomainToEntities(game)
        appExecutors.diskIO.execute {
            localDataSource.deleteGameFromWishlist(wishlistEntity)
        }
    }

    override fun getWishlist(): Flow<List<Game>> {
        return localDataSource.getWishlist().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }
}