package com.example.animalsupermarket.model

data class Address(
    val id: Int,
    val recipientName: String,
    val phoneNumber: String,
    val address: String,
    val isDefault: Boolean
)