package com.example.dolananlist.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dolananlist.core.di.Injection
import com.example.dolananlist.core.domain.usecase.GameUseCase
import com.example.dolananlist.detailgame.DetailViewModel
import com.example.dolananlist.gamewishlist.WishlistViewModel
import com.example.dolananlist.gamewishlist.domain.usecase.WishlistUseCase
import com.example.dolananlist.listgame.MainViewModel

class ViewModelFactory private constructor(
    private val wishlistUseCase: WishlistUseCase,
    private val gameUseCase: GameUseCase
) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(gameUseCase) as T
        }else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(wishlistUseCase,gameUseCase) as T
        } else if (modelClass.isAssignableFrom(WishlistViewModel::class.java)) {
            return WishlistViewModel(wishlistUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(
                Injection.provideWishlistUseCase(context),
                Injection.provideGameUseCase()
            )
        }.also { instance = it }
    }
}