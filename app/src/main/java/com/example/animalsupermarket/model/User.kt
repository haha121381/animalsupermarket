package com.example.animalsupermarket.model

data class User(
    val name: String,
    val email: String,
    val avatarUrl: String,
    val orderCount: Int,
    val couponCount: Int
)