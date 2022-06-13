package com.example.dolananlist.model

import com.google.gson.annotations.SerializedName

data class GameResponse(

	@field:SerializedName("results")
	val results: List<ResultsItem>
)

data class ParentPlatformsItem(

	@field:SerializedName("platform")
	val platform: Platform
)

data class ResultsItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("background_image")
    val backgroundImage: String,

    @field:SerializedName("genres")
    val genres: List<GenresItem>,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatformsItem>
)

data class Platform(

	@field:SerializedName("name")
	val name: String
)

data class GenresItem(

	@field:SerializedName("name")
	val name: String
)
