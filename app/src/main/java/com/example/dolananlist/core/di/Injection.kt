package com.example.dolananlist.core.di

import android.content.Context
import com.example.dolananlist.core.data.GameRepository
import com.example.dolananlist.core.data.remote.RemoteDataSource
import com.example.dolananlist.core.data.remote.retrofit.ApiConfig
import com.example.dolananlist.core.domain.repository.IGameRepository
import com.example.dolananlist.core.domain.usecase.GameInteractor
import com.example.dolananlist.core.domain.usecase.GameUseCase
import com.example.dolananlist.core.utils.AppExecutors
import com.example.dolananlist.gamewishlist.data.WishlistRepository
import com.example.dolananlist.gamewishlist.data.local.LocalDataSource
import com.example.dolananlist.gamewishlist.data.local.room.WishlistDatabase
import com.example.dolananlist.gamewishlist.domain.repository.IWishlistRepository
import com.example.dolananlist.gamewishlist.domain.usecase.WishlistInteractor
import com.example.dolananlist.gamewishlist.domain.usecase.WishlistUseCase

object Injection {
    private fun provideWishlistRepository(context: Context): IWishlistRepository {
        val database = WishlistDatabase.getInstance(context)
        val dao = database.wishlistDao()

        val localDataSource = LocalDataSource.getInstance(dao)
        val appExecutors = AppExecutors()
        return WishlistRepository.getInstance(localDataSource, appExecutors)
    }

    private fun provideGameRepository(): IGameRepository {
        val api = ApiConfig.getApiService()

        val remoteDataSource = RemoteDataSource.getInstance(api)
        return GameRepository.getInstance(remoteDataSource)
    }

    fun provideWishlistUseCase(context: Context): WishlistUseCase {
        val repository = provideWishlistRepository(context)
        return WishlistInteractor(repository)
    }

    fun provideGameUseCase(): GameUseCase {
        val repository = provideGameRepository()
        return GameInteractor(repository)
    }
}