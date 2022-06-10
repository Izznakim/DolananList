package com.example.dolananlist.retrofit

import com.example.dolananlist.GameResponse
import com.example.dolananlist.model.GameDetailResponse
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