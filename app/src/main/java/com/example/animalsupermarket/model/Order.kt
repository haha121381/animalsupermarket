package com.example.animalsupermarket.model

import com.example.animalsupermarket.domain.model.CartItem
import java.util.Date

data class Order(
    val id: String,
    val items: List<CartItem>,
    val totalPrice: Double,
    val date: Date,
    val status: String // e.g., "Processing", "Shipped", "Delivered"
)