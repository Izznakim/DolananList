package com.example.dolananlist.core.domain.model

import com.example.dolananlist.core.data.source.remote.response.*

data class GameDetail(
    val id: Int,
    val backgroundImage: String,
    val developers: List<DevelopersItem>,
    val genres: List<GenresDetailItem>,
    val name: String,
    val publishers: List<PublishersItem>,
    val alternativeNames: List<String>,
    val descriptionRaw: String,
    val released: String,
    val platforms: List<PlatformsItem>,
    val tags: List<TagsItem>
)
