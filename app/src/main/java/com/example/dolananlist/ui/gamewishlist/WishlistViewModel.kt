package com.example.dolananlist.ui.gamewishlist

import androidx.lifecycle.ViewModel
import com.example.dolananlist.data.WishlistRepository

class WishlistViewModel(private val wishlistRepository: WishlistRepository) : ViewModel() {
    fun getWishlist() = wishlistRepository.getWishlist()
}