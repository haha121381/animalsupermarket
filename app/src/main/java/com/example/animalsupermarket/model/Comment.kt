package com.example.animalsupermarket.model

import java.util.Date

data class Comment(
    val id: Int,
    val productId: Int,
    val userName: String,
    val userAvatarUrl: String,
    val rating: Int,
    val text: String,
    val date: Date
)