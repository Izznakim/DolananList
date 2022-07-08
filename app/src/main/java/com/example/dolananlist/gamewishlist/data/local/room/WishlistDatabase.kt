package com.example.dolananlist.gamewishlist.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dolananlist.gamewishlist.data.local.entity.WishlistEntity

@Database(entities = [WishlistEntity::class], version = 1, exportSchema = false)
abstract class WishlistDatabase : RoomDatabase() {
    abstract fun wishlistDao(): WishlistDao

    companion object {
        @Volatile
        private var instance: WishlistDatabase? = null
        fun getInstance(context: Context): WishlistDatabase = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                WishlistDatabase::class.java, "Wishlist.db"
            ).build()
        }
    }
}