package com.example.dolananlist.core.di

import com.example.dolananlist.core.BuildConfig
import com.example.dolananlist.core.data.GameRepository
import com.example.dolananlist.core.data.remote.RemoteDataSource
import com.example.dolananlist.core.data.remote.retrofit.ApiService
import com.example.dolananlist.core.domain.repository.IGameRepository
import com.example.dolananlist.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
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

val repositoryCoreModule = module {
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IGameRepository> { GameRepository(get()) }
}