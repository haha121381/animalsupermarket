package com.example.animalsupermarket.repository

import com.example.animalsupermarket.model.User

class UserRepository {
    fun getUser(): User {
        // In a real app, this would fetch data from an API or local database
        return User(
            name = "测试用户",
            avatarUrl = "https://img.alicdn.com/imgextra/i4/2200748495329/O1CN01b6A3b61sY2Zz4Zz3j_!!2200748495329.jpg",
            orderCount = 5,
            couponCount = 3,
            email = "test@example.com"
        )
    }
}