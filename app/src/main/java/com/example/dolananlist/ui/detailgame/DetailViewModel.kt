package com.example.dolananlist.ui.detailgame

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dolananlist.BuildConfig
import com.example.dolananlist.data.WishlistRepository
import com.example.dolananlist.data.remote.response.GameDetailResponse
import com.example.dolananlist.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val wishlistRepository: WishlistRepository) : ViewModel() {
    private val _gameDetail = MutableLiveData<GameDetailResponse>()
    val gameDetail: LiveData<GameDetailResponse> = _gameDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isWish = MutableLiveData<Boolean>()
    val isWish: LiveData<Boolean> = _isWish

    fun getGameDetail(id: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getGameDetail(id, BuildConfig.API_KEY)
        client.enqueue(object : Callback<GameDetailResponse> {
            override fun onResponse(
                call: Call<GameDetailResponse>,
                response: Response<GameDetailResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _gameDetail.value = response.body()
                } else {
                    Log.d("getGameDetail", "onResponse: Something Error")
                }
            }

            override fun onFailure(call: Call<GameDetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d("getGameDetail", "onFailure: ${t.message}")
            }
        })
    }

    fun setGameToWishlist(game: GameDetailResponse) {
        wishlistRepository.setGameWishlist(game)
    }

    fun checkExistOrNot(id: Int) {
        viewModelScope.launch {
            _isWish.value = wishlistRepository.checkExistOrNot(id)
        }
    }

    fun deleteGameFromWishlist(game: GameDetailResponse) {
        wishlistRepository.deleteGameFromWishlist(game)
    }
}