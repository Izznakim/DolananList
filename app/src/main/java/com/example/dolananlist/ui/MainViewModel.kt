package com.example.dolananlist.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dolananlist.GameResponse
import com.example.dolananlist.ResultsItem
import com.example.dolananlist.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _listGame = MutableLiveData<List<ResultsItem>>()
    val listGame: LiveData<List<ResultsItem>> = _listGame

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getListGame()
    }

    private fun getListGame() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getGameList("380488d9a4a4466c9a89fa586b0617ab")
        client.enqueue(object : Callback<GameResponse> {
            override fun onResponse(call: Call<GameResponse>, response: Response<GameResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listGame.value = response.body()?.results
                } else {
                    Log.d("getListGame", "onResponse: Something Error")
                }
            }

            override fun onFailure(call: Call<GameResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d("getListGame", "onFailure: ${t.message}")
            }
        })
    }
}