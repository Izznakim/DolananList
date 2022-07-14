package com.example.dolananlist.detailgame

import androidx.lifecycle.*
import com.example.dolananlist.core.data.source.remote.response.GameDetailResponse
import com.example.dolananlist.core.domain.model.GameDetail
import com.example.dolananlist.core.domain.usecase.WishlistUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val wishlistUseCase: WishlistUseCase, private val gameUseCase: com.example.dolananlist.core.domain.usecase.GameUseCase) : ViewModel() {

    private val _isWish = MutableLiveData<Boolean>()
    val isWish: LiveData<Boolean> = _isWish

    fun getGameDetail(id: Int) =
        gameUseCase.getGameDetail(id).asLiveData()

    fun setGameToWishlist(game: GameDetail) {
        wishlistUseCase.setGameWishlist(game)
    }

    fun checkExistOrNot(id: Int) {
        viewModelScope.launch {
            _isWish.value = wishlistUseCase.checkExistOrNot(id)
        }
    }

    fun deleteGameFromWishlist(game: GameDetail) {
        wishlistUseCase.deleteGameFromWishlist(game)
    }
}