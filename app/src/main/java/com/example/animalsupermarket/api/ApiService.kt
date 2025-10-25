package com.example.animalsupermarket.api

import com.example.animalsupermarket.model.Product
import com.example.animalsupermarket.repository.getSampleProducts
import kotlinx.coroutines.delay

class ApiService {
    suspend fun getProducts(): List<Product> {
        delay(1000) // Simulate network delay
        return getSampleProducts()
    }

    suspend fun getProductById(productId: Int): Product? {
        delay(500) // Simulate network delay
        return getSampleProducts().find { it.id == productId }
    }
}