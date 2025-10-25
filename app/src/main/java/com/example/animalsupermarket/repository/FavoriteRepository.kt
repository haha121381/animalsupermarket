package com.example.animalsupermarket.repository

import com.example.animalsupermarket.dao.FavoriteProductDao
import com.example.animalsupermarket.model.FavoriteProduct
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor(private val favoriteProductDao: FavoriteProductDao) {

    fun getAll(): Flow<List<FavoriteProduct>> {
        return favoriteProductDao.getAll()
    }

    suspend fun insert(favoriteProduct: FavoriteProduct) {
        favoriteProductDao.insert(favoriteProduct)
    }

    suspend fun delete(favoriteProduct: FavoriteProduct) {
        favoriteProductDao.delete(favoriteProduct)
    }
}