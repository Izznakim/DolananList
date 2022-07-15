package com.example.dolananlist.core.utils

import com.example.dolananlist.core.data.source.local.entity.WishlistEntity
import com.example.dolananlist.core.data.source.remote.response.GameDetailResponse
import com.example.dolananlist.core.data.source.remote.response.ResultsItem
import com.example.dolananlist.core.data.source.remote.retrofit.ApiResponse
import com.example.dolananlist.core.domain.model.Game
import com.example.dolananlist.core.domain.model.GameDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object DataMapper {
    fun mapDomainToEntities(input: GameDetail): WishlistEntity =
        WishlistEntity(
            input.id,
            input.backgroundImage,
            input.genres,
            input.name,
            input.platforms
        )

    fun mapEntitiesToDomain(input: List<WishlistEntity>): List<Game> = input.map {
        Game(it.id, it.backgroundImage, it.genres, it.name, it.platforms)
    }

    fun mapResponseToDomainGameDetail(
        input: Flow<ApiResponse<GameDetailResponse>>
    ): Flow<ApiResponse<GameDetail>> {
        return flow {
            try {
                input.collect { apiResponse ->
                    when (apiResponse) {
                        is ApiResponse.Success -> {
                            val response = apiResponse.data
                            val gameDetail = GameDetail(
                                response.id,
                                response.backgroundImage,
                                response.developers.joinToString { it.name },
                                response.genres.joinToString { it.name },
                                response.name,
                                response.publishers.joinToString { it.name },
                                response.alternativeNames.joinToString { it },
                                response.descriptionRaw,
                                response.released,
                                response.platforms.joinToString { it.platform.name },
                                response.tags.joinToString { it.name }
                            )
                            emit(ApiResponse.Success(gameDetail))
                        }
                        is ApiResponse.Error->emit(ApiResponse.Error(apiResponse.errorMessage))
                        else -> {}
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun mapResponseToDomainGameList(
        input: Flow<ApiResponse<List<ResultsItem>>>
    ): Flow<ApiResponse<List<Game>>> {
        return flow {
            try {
                input.collect { apiResponse ->
                    when (apiResponse) {
                        is ApiResponse.Success -> {
                            val gameList = ArrayList<Game>()
                            val response = apiResponse.data
                            gameList.addAll(response.map { result->
                                Game(
                                    result.id,
                                    result.backgroundImage,
                                    result.genres.joinToString { it.name },
                                    result.name,
                                    result.parentPlatforms.joinToString { it.platform.name }
                                )
                            })
                            emit(ApiResponse.Success(gameList))
                        }
                        is ApiResponse.Error->emit(ApiResponse.Error(apiResponse.errorMessage))
                        else -> {}
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}