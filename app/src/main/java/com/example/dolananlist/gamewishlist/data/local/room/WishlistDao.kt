package com.example.dolananlist.gamewishlist.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dolananlist.gamewishlist.data.local.entity.WishlistEntity

@Dao
interface WishlistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGameToWishlist(wishlist: List<WishlistEntity>)

    @Query("SELECT * FROM wishlist")
    fun getWishlist(): LiveData<List<WishlistEntity>>

    @Query("SELECT EXISTS(SELECT * FROM wishlist WHERE id LIKE '%' || :id || '%')")
    suspend fun checkExistOrNot(id: Int): Boolean

    @Delete
    fun deleteGameFromWishlist(wishlist: WishlistEntity)
}