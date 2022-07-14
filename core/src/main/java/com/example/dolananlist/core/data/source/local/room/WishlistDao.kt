package com.example.dolananlist.core.data.source.local.room

import androidx.room.*
import com.example.dolananlist.core.data.source.local.entity.WishlistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGameToWishlist(wishlist: List<WishlistEntity>)

    @Query("SELECT * FROM wishlist")
    fun getWishlist(): Flow<List<WishlistEntity>>

    @Query("SELECT EXISTS(SELECT * FROM wishlist WHERE id LIKE '%' || :id || '%')")
    suspend fun checkExistOrNot(id: Int): Boolean

    @Delete
    fun deleteGameFromWishlist(wishlist: WishlistEntity)
}