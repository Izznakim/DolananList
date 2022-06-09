package com.example.dolananlist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val name: String,
    val imageUrl: String,
    val rating: Int,
    val ratingTop: Int,
    val platform: List<String>, // Parent Platform
    val genres: List<String>
) : Parcelable
