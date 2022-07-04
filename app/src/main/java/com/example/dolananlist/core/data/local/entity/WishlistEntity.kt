package com.example.dolananlist.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlist")
class WishlistEntity(
    @field:PrimaryKey
    val id: Int,
    val backgroundImage: String,
    val genres: String,
    val name: String,
    val platforms: String
)