package com.example.dolananlist.core.di

import android.content.Context
import com.example.dolananlist.gamewishlist.data.WishlistRepository
import com.example.dolananlist.gamewishlist.data.local.room.WishlistDatabase
import com.example.dolananlist.core.utils.AppExecutors
import com.example.dolananlist.gamewishlist.data.local.LocalDataSource
import com.example.dolananlist.gamewishlist.domain.repository.IWishlistRepository
import com.example.dolananlist.gamewishlist.domain.usecase.WishlistInteractor
import com.example.dolananlist.gamewishlist.domain.usecase.WishlistUseCase

object Injection {
    private fun provideRepository(context: Context): IWishlistRepository {
        val database = WishlistDatabase.getInstance(context)
        val dao = database.wishlistDao()

        val localDataSource=LocalDataSource.getInstance(dao)
        val appExecutors = AppExecutors()
        return WishlistRepository.getInstance(localDataSource, appExecutors)
    }

    fun provideWishlistUseCase(context: Context):WishlistUseCase{
        val repository= provideRepository(context)
        return WishlistInteractor(repository)
    }
}