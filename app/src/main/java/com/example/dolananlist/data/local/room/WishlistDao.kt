package com.example.dolananlist.data.local.room

import androidx.room.*
import com.example.dolananlist.data.local.entity.WishlistEntity

@Dao
interface WishlistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGameToWishlist(wishlist: List<WishlistEntity>)

    @Query("SELECT EXISTS(SELECT * FROM wishlist WHERE id LIKE '%' || :id || '%')")
    suspend fun checkExistOrNot(id: Int): Boolean

    @Delete
    fun deleteGameFromWishlist(wishlist: WishlistEntity)
}