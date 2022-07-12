package com.example.dolananlist

import android.app.Application
import com.example.dolananlist.core.di.networkModule
import com.example.dolananlist.core.di.repositoryCoreModule
import com.example.dolananlist.di.useCaseModule
import com.example.dolananlist.di.viewModelModule
import com.example.dolananlist.gamewishlist.di.databaseModule
import com.example.dolananlist.gamewishlist.di.repositoryWishlistModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryCoreModule,
                    repositoryWishlistModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}