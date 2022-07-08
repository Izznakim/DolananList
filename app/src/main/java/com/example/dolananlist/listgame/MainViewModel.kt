package com.example.dolananlist.listgame

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dolananlist.BuildConfig
import com.example.dolananlist.core.data.remote.response.GameResponse
import com.example.dolananlist.core.data.remote.response.ResultsItem
import com.example.dolananlist.core.data.remote.retrofit.ApiConfig
import com.example.dolananlist.core.domain.usecase.GameUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    init {
        getGameList()
    }

    fun getGameList() =
        gameUseCase.getGameList()
}