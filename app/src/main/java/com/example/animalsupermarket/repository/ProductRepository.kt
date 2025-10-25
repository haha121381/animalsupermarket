package com.example.animalsupermarket.repository

import com.example.animalsupermarket.api.ApiService
import com.example.animalsupermarket.model.Product

class ProductRepository(private val apiService: ApiService) {
    suspend fun getProducts(): List<Product> {
        return apiService.getProducts()
    }

    suspend fun getProductById(productId: Int): Product? {
        return apiService.getProductById(productId)
    }
}