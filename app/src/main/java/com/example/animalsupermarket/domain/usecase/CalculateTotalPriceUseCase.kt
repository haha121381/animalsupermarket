package com.example.animalsupermarket.domain.usecase

import com.example.animalsupermarket.domain.model.CartItem
import javax.inject.Inject

class CalculateTotalPriceUseCase @Inject constructor() {
    operator fun invoke(cartItems: List<CartItem>): Double {
        return cartItems.sumOf { it.product.price * it.quantity }
    }
}