package com.example.dolananlist

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

	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("rating_top")
	val ratingTop: Int,

	@field:SerializedName("genres")
	val genres: List<GenresItem>,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("rating")
	val rating: Double,

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
