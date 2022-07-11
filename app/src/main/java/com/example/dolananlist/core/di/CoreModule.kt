package com.example.dolananlist.core.di

import androidx.room.Room
import androidx.viewbinding.BuildConfig
import com.example.dolananlist.core.data.GameRepository
import com.example.dolananlist.core.data.remote.RemoteDataSource
import com.example.dolananlist.core.data.remote.retrofit.ApiService
import com.example.dolananlist.core.domain.repository.IGameRepository
import com.example.dolananlist.core.utils.AppExecutors
import com.example.dolananlist.gamewishlist.data.WishlistRepository
import com.example.dolananlist.gamewishlist.data.local.LocalDataSource
import com.example.dolananlist.gamewishlist.data.local.room.WishlistDatabase
import com.example.dolananlist.gamewishlist.domain.repository.IWishlistRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory { get<WishlistDatabase>().wishlistDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            WishlistDatabase::class.java, "Wishlist.db"
        ).build()
    }
}

val networkModule= module {
    single {
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule= module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IWishlistRepository> { WishlistRepository(get(),get()) }
    single<IGameRepository> { GameRepository(get()) }
}