package com.example.dolananlist.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dolananlist.core.data.source.local.entity.WishlistEntity

@Database(entities = [WishlistEntity::class], version = 1, exportSchema = false)
abstract class WishlistDatabase : RoomDatabase() {
    abstract fun wishlistDao(): WishlistDao

    companion object
}