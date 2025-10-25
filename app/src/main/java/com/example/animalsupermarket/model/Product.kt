package com.example.animalsupermarket.model

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String,
    val sales: Int,
    val monthlySales: Int,
    val rating: Double,
    val brand: String,
    val stock: Int,
    val specifications: List<String>
)