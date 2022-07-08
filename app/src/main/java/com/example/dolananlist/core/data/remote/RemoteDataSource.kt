package com.example.dolananlist.core.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dolananlist.BuildConfig
import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import com.example.dolananlist.core.data.remote.response.GameResponse
import com.example.dolananlist.core.data.remote.response.ResultsItem
import com.example.dolananlist.core.data.remote.retrofit.ApiResponse
import com.example.dolananlist.core.data.remote.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {
    fun getGameList(): LiveData<ApiResponse<List<ResultsItem>>> {
        val resultData = MutableLiveData<ApiResponse<List<ResultsItem>>>()

        resultData.value=ApiResponse.Loading
        val client = apiService.getGameList(BuildConfig.API_KEY)
        client.enqueue(object : Callback<GameResponse> {
            override fun onResponse(call: Call<GameResponse>, response: Response<GameResponse>) {
                if (response.isSuccessful) {
                    val listGame = response.body()?.results
                    resultData.value =
                        if (listGame != null) ApiResponse.Success(listGame) else ApiResponse.Empty
                } else {
                    Log.d("getListGame", "onResponse: Something Error")
                }
            }

            override fun onFailure(call: Call<GameResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.d("getListGame", "onFailure: ${t.message}")
            }
        })

        return resultData
    }

    fun getGameDetail(id: Int): LiveData<ApiResponse<GameDetailResponse>> {
        val resultData = MutableLiveData<ApiResponse<GameDetailResponse>>()

        resultData.value=ApiResponse.Loading
        val client = apiService.getGameDetail(id, BuildConfig.API_KEY)
        client.enqueue(object : Callback<GameDetailResponse> {
            override fun onResponse(
                call: Call<GameDetailResponse>,
                response: Response<GameDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val gameDetail = response.body()
                    resultData.value =
                        if (gameDetail != null) ApiResponse.Success(gameDetail) else ApiResponse.Empty
                } else {
                    Log.d("getGameDetail", "onResponse: Something Error")
                }
            }

            override fun onFailure(call: Call<GameDetailResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.d("getGameDetail", "onFailure: ${t.message}")
            }
        })

        return resultData
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource(apiService)
        }
    }
}