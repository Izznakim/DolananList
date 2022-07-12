package com.example.dolananlist.detailgame

import androidx.lifecycle.*
import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import com.example.dolananlist.gamewishlist.domain.usecase.WishlistUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val wishlistUseCase: WishlistUseCase, private val gameUseCase: com.example.dolananlist.core.domain.usecase.GameUseCase) : ViewModel() {

    private val _isWish = MutableLiveData<Boolean>()
    val isWish: LiveData<Boolean> = _isWish

    fun getGameDetail(id: Int) =
        gameUseCase.getGameDetail(id).asLiveData()

    fun setGameToWishlist(game: GameDetailResponse) {
        wishlistUseCase.setGameWishlist(game)
    }

    fun checkExistOrNot(id: Int) {
        viewModelScope.launch {
            _isWish.value = wishlistUseCase.checkExistOrNot(id)
        }
    }

    fun deleteGameFromWishlist(game: GameDetailResponse) {
        wishlistUseCase.deleteGameFromWishlist(game)
    }
}