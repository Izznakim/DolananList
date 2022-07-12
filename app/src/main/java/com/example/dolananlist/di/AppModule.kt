package com.example.dolananlist.di

import com.example.dolananlist.detailgame.DetailViewModel
import com.example.dolananlist.core.domain.usecase.WishlistInteractor
import com.example.dolananlist.core.domain.usecase.WishlistUseCase
import com.example.dolananlist.listgame.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule= module {
    factory<WishlistUseCase> { WishlistInteractor(get()) }
    factory<com.example.dolananlist.core.domain.usecase.GameUseCase> {
        com.example.dolananlist.core.domain.usecase.GameInteractor(
            get()
        )
    }
}

val viewModelModule= module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get(),get()) }
}