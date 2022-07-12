package com.example.dolananlist.gamewishlist.di

import androidx.room.Room
import com.example.dolananlist.gamewishlist.data.WishlistRepository
import com.example.dolananlist.gamewishlist.data.local.LocalDataSource
import com.example.dolananlist.gamewishlist.data.local.room.WishlistDatabase
import com.example.dolananlist.gamewishlist.domain.repository.IWishlistRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val databaseModule = module {
    factory { get<WishlistDatabase>().wishlistDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            WishlistDatabase::class.java, "Wishlist.db"
        ).build()
    }
}

val repositoryWishlistModule = module {
    single { LocalDataSource(get()) }
    single<IWishlistRepository> { WishlistRepository(get(), get()) }
}