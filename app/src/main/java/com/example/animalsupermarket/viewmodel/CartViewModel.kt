package com.example.animalsupermarket.viewmodel

import androidx.lifecycle.ViewModel
import com.example.animalsupermarket.domain.usecase.CalculateTotalPriceUseCase
import com.example.animalsupermarket.model.Coupon
import com.example.animalsupermarket.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class CartItem(val product: Product, val quantity: Int)

@HiltViewModel
class CartViewModel @Inject constructor(
    private val calculateTotalPriceUseCase: CalculateTotalPriceUseCase
) : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    fun addToCart(product: Product) {
        _cartItems.update { currentCart ->
            val cartItem = currentCart.find { it.product.id == product.id }
            if (cartItem != null) {
                return@update currentCart.map {
                    if (it.product.id == product.id) {
                        it.copy(quantity = it.quantity + 1)
                    } else {
                        it
                    }
                }
            } else {
                currentCart + CartItem(product, 1)
            }
        }
    }

    fun removeFromCart(productId: Int) {
        _cartItems.update { currentCart ->
            currentCart.filterNot { it.product.id == productId }
        }
    }

    fun increaseQuantity(productId: Int) {
        _cartItems.update { currentCart ->
            currentCart.map {
                if (it.product.id == productId) {
                    it.copy(quantity = it.quantity + 1)
                } else {
                    it
                }
            }
        }
    }

    fun decreaseQuantity(productId: Int) {
        _cartItems.update { currentCart ->
            val cartItem = currentCart.find { it.product.id == productId }
            if (cartItem != null && cartItem.quantity > 1) {
                currentCart.map {
                    if (it.product.id == productId) {
                        it.copy(quantity = it.quantity - 1)
                    } else {
                        it
                    }
                }
            } else {
                currentCart.filterNot { it.product.id == productId }
            }
        }
    }

    fun getTotalPrice(): Double {
        return calculateTotalPriceUseCase(_cartItems.value.map { com.example.animalsupermarket.domain.model.CartItem(it.product, it.quantity) })
    }
    fun clearCart() {
        _cartItems.update { emptyList() }
    }

    fun getTotalPrice(coupon: Coupon? = null): Double {
        val totalPrice = calculateTotalPriceUseCase(_cartItems.value.map { com.example.animalsupermarket.domain.model.CartItem(it.product, it.quantity) })
        return if (coupon != null) {
            when (coupon.id) {
                1 -> if (totalPrice >= 100) totalPrice - 10 else totalPrice
                2 -> totalPrice - 5
                else -> totalPrice
            }
        } else {
            totalPrice
        }
    }
}