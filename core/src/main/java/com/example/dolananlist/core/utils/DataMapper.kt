package com.example.dolananlist.core.utils

import com.example.dolananlist.core.data.remote.response.GameDetailResponse
import com.example.dolananlist.core.data.local.entity.WishlistEntity
import com.example.dolananlist.core.domain.model.Wishlist

object DataMapper {
    fun mapResponsesToEntities(input: GameDetailResponse): WishlistEntity =
        WishlistEntity(
            input.id,
            input.backgroundImage,
            input.genres.joinToString { it.name },
            input.name,
            input.platforms.joinToString { it.platform.name })

    fun mapEntitiesToDomain(input: List<WishlistEntity>): List<Wishlist> = input.map {
        Wishlist(it.id, it.backgroundImage, it.genres, it.name, it.platforms)
    }
}