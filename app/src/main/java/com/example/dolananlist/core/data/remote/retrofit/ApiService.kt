package com.example.dolananlist.core.data.remote.retrofit

import com.example.dolananlist.core.data.remote.response.GameResponse
import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    fun getGameList(@Query("key") key: String): Call<GameResponse>

    @GET("games/{id}")
    fun getGameDetail(@Path("id") id: Int, @Query("key") key: String): Call<GameDetailResponse>
}