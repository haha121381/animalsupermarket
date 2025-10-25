package com.example.animalsupermarket.dao

import androidx.room.*
import com.example.animalsupermarket.model.FavoriteProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteProductDao {
    @Query("SELECT * FROM favorite_products")
    fun getAll(): Flow<List<FavoriteProduct>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteProduct: FavoriteProduct)

    @Delete
    suspend fun delete(favoriteProduct: FavoriteProduct)
}