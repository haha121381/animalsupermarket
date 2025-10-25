package com.example.animalsupermarket.viewmodel

import androidx.lifecycle.ViewModel
import com.example.animalsupermarket.model.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor() : ViewModel() {

    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders.asStateFlow()

    fun placeOrder(cartItems: List<CartItem>, totalPrice: Double) {
        val newOrder = Order(
            id = UUID.randomUUID().toString(),
            items = cartItems.map { com.example.animalsupermarket.domain.model.CartItem(it.product, it.quantity) },
            totalPrice = totalPrice,
            date = Date(),
            status = "Processing"
        )
        _orders.update { currentOrders ->
            currentOrders + newOrder
        }
    }
}