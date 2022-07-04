package com.example.dolananlist.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dolananlist.core.data.WishlistRepository
import com.example.dolananlist.core.di.Injection
import com.example.dolananlist.detailgame.DetailViewModel
import com.example.dolananlist.gamewishlist.WishlistViewModel
import com.example.dolananlist.gamewishlist.domain.usecase.WishlistUseCase

class ViewModelFactory private constructor(private val wishlistUseCase: WishlistUseCase) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(wishlistUseCase) as T
        } else if (modelClass.isAssignableFrom(WishlistViewModel::class.java)) {
            return WishlistViewModel(wishlistUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideWishlistUseCase(context))
        }.also { instance = it }
    }
}