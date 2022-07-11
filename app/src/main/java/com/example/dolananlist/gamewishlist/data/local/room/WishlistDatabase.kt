package com.example.dolananlist.gamewishlist.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dolananlist.gamewishlist.data.local.entity.WishlistEntity

@Database(entities = [WishlistEntity::class], version = 1, exportSchema = false)
abstract class WishlistDatabase : RoomDatabase() {
    abstract fun wishlistDao(): WishlistDao

    companion object
}