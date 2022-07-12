package com.example.dolananlist.wishlist

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wishlistModule= module {
    viewModel { WishlistViewModel(get()) }
}