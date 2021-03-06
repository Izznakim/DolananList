package com.example.dolananlist.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.dolananlist.core.domain.usecase.WishlistUseCase

class WishlistViewModel(wishlistUseCase: WishlistUseCase) : ViewModel() {
    val wishlist = wishlistUseCase.getWishlist().asLiveData()
}