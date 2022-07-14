package com.example.dolananlist.core.data.source.remote.retrofit

import com.example.dolananlist.core.data.source.remote.response.GameDetailResponse
import com.example.dolananlist.core.data.source.remote.response.GameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGameList(@Query("key") key: String): GameResponse

    @GET("games/{id}")
    suspend fun getGameDetail(@Path("id") id: Int, @Query("key") key: String): GameDetailResponse
}