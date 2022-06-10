package com.example.dolananlist.model

import com.google.gson.annotations.SerializedName

data class GameDetailResponse(

    @field:SerializedName("background_image")
    val backgroundImage: String,

    @field:SerializedName("developers")
    val developers: List<DevelopersItem>,

    @field:SerializedName("genres")
    val genres: List<GenresItem>,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("publishers")
    val publishers: List<PublishersItem>,

    @field:SerializedName("alternative_names")
    val alternativeNames: List<String>,

    @field:SerializedName("description_raw")
    val descriptionRaw: String,

    @field:SerializedName("released")
    val released: String,

    @field:SerializedName("platforms")
    val platforms: List<PlatformsItem>,

    @field:SerializedName("tags")
    val tags: List<TagsItem>
)

data class GenresItem(

    @field:SerializedName("name")
    val name: String
)

data class Platform(

    @field:SerializedName("name")
    val name: String
)

data class PlatformsItem(

    @field:SerializedName("platform")
    val platform: Platform
)

data class PublishersItem(

    @field:SerializedName("name")
    val name: String
)

data class DevelopersItem(

    @field:SerializedName("name")
    val name: String
)

data class TagsItem(

    @field:SerializedName("name")
    val name: String
)
