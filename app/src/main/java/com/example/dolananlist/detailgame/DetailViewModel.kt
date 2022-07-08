package com.example.dolananlist.detailgame

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dolananlist.BuildConfig
import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import com.example.dolananlist.core.data.remote.retrofit.ApiConfig
import com.example.dolananlist.core.domain.usecase.GameUseCase
import com.example.dolananlist.gamewishlist.domain.usecase.WishlistUseCase
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val wishlistUseCase: WishlistUseCase, private val gameUseCase: GameUseCase) : ViewModel() {

    private val _isWish = MutableLiveData<Boolean>()
    val isWish: LiveData<Boolean> = _isWish

    fun getGameDetail(id: Int) =
        gameUseCase.getGameDetail(id)

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