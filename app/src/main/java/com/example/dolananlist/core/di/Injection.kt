package com.example.dolananlist.core.di

import android.content.Context
import com.example.dolananlist.core.data.WishlistRepository
import com.example.dolananlist.core.data.local.room.WishlistDatabase
import com.example.dolananlist.core.utils.AppExecutors
import com.example.dolananlist.gamewishlist.domain.repository.IWishlistRepository
import com.example.dolananlist.gamewishlist.domain.usecase.WishlistInteractor
import com.example.dolananlist.gamewishlist.domain.usecase.WishlistUseCase

object Injection {
    fun provideRepository(context: Context): IWishlistRepository {
        val database = WishlistDatabase.getInstance(context)
        val dao = database.wishlistDao()
        val appExecutors = AppExecutors()
        return WishlistRepository.getInstance(dao, appExecutors)
    }

    fun provideWishlistUseCase(context: Context):WishlistUseCase{
        val repository= provideRepository(context)
        return WishlistInteractor(repository)
    }
}