package com.example.dolananlist.core.domain.model

data class GameDetail(
    val id: Int,
    val backgroundImage: String,
    val developers: String,
    val genres: String,
    val name: String,
    val publishers: String,
    val alternativeNames: String,
    val descriptionRaw: String,
    val released: String,
    val platforms: String,
    val tags: String
)
