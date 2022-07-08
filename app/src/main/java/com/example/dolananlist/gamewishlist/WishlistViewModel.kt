package com.example.dolananlist.gamewishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.dolananlist.gamewishlist.domain.usecase.WishlistUseCase

class WishlistViewModel(wishlistUseCase: WishlistUseCase) : ViewModel() {
    val wishlist = wishlistUseCase.getWishlist().asLiveData()
}