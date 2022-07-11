package com.example.dolananlist.listgame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.dolananlist.core.domain.usecase.GameUseCase

class MainViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    init {
        getGameList()
    }

    fun getGameList() =
        gameUseCase.getGameList().asLiveData()
}