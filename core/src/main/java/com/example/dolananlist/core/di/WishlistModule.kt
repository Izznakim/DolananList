package com.example.dolananlist.core.di

import androidx.room.Room
import com.example.dolananlist.core.data.WishlistRepository
import com.example.dolananlist.core.data.source.local.LocalDataSource
import com.example.dolananlist.core.data.source.local.room.WishlistDatabase
import com.example.dolananlist.core.domain.repository.IWishlistRepository
import com.example.dolananlist.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val databaseModule = module {
    factory { get<WishlistDatabase>().wishlistDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("inaki".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            WishlistDatabase::class.java, "Game.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val repositoryWishlistModule = module {
    single { LocalDataSource(get()) }
    factory { AppExecutors() }
    single<IWishlistRepository> { WishlistRepository(get(), get()) }
}