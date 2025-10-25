package com.example.animalsupermarket.model

import java.util.Date

data class Coupon(
    val id: Int,
    val description: String,
    val expiryDate: Date
)