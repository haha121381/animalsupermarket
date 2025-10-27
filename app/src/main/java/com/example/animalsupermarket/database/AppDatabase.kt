package com.example.animalsupermarket.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.animalsupermarket.dao.FavoriteProductDao
import com.example.animalsupermarket.model.FavoriteProduct

@Database(entities = [FavoriteProduct::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteProductDao(): FavoriteProductDao
}