package com.example.dolananlist.core.utils

import android.util.Log
import com.example.dolananlist.core.BuildConfig
import com.example.dolananlist.core.data.source.local.entity.WishlistEntity
import com.example.dolananlist.core.data.source.remote.response.GameDetailResponse
import com.example.dolananlist.core.data.source.remote.retrofit.ApiResponse
import com.example.dolananlist.core.data.source.remote.retrofit.ApiService
import com.example.dolananlist.core.domain.model.GameDetail
import com.example.dolananlist.core.domain.model.Wishlist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object DataMapper {
    fun mapDomainToEntities(input: GameDetail): WishlistEntity =
        WishlistEntity(
            input.id,
            input.backgroundImage,
            input.genres.joinToString { it.name },
            input.name,
            input.platforms.joinToString { it.platform.name }
        )

    fun mapEntitiesToDomain(input: List<WishlistEntity>): List<Wishlist> = input.map {
        Wishlist(it.id, it.backgroundImage, it.genres, it.name, it.platforms)
    }

    fun mapResponseToDomain(
        apiService: ApiService,
        input: Flow<ApiResponse<GameDetailResponse>>,
        id: Int
    ): Flow<ApiResponse<GameDetail>> {
        Log.d("GameRepository", "mapResponseToDomain: $input")
        return flow {
            try {
                val response = apiService.getGameDetail(id, BuildConfig.API_KEY)
                val gameDetail = GameDetail(
                    response.id,
                    response.backgroundImage,
                    response.developers,
                    response.genres,
                    response.name,
                    response.publishers,
                    response.alternativeNames,
                    response.descriptionRaw,
                    response.released,
                    response.platforms,
                    response.tags
                )
                emit(ApiResponse.Success(gameDetail))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
//        return GameDetail(
//            input.id,
//            input.backgroundImage,
//            input.developers,
//            input.genres,
//            input.name,
//            input.publishers,
//            input.alternativeNames,
//            input.descriptionRaw,
//            input.released,
//            input.platforms,
//            input.tags
//        )
    }
}