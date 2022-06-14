package com.example.dolananlist.di

import android.content.Context
import com.example.dolananlist.data.WishlistRepository
import com.example.dolananlist.data.local.room.WishlistDatabase
import com.example.dolananlist.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): WishlistRepository {
        val database = WishlistDatabase.getInstance(context)
        val dao = database.wishlistDao()
        val appExecutors = AppExecutors()
        return WishlistRepository.getInstance(dao, appExecutors)
    }
}