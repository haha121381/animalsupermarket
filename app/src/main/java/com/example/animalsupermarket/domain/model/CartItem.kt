package com.example.animalsupermarket.domain.model

import com.example.animalsupermarket.model.Product

data class CartItem(val product: Product, val quantity: Int)