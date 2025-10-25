package com.example.animalsupermarket.viewmodel

import androidx.lifecycle.ViewModel
import com.example.animalsupermarket.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor() : ViewModel() {

    private val _history = MutableStateFlow<List<Product>>(emptyList())
    val history: StateFlow<List<Product>> = _history.asStateFlow()

    fun addToHistory(product: Product) {
        _history.update { currentHistory ->
            val updatedHistory = currentHistory.toMutableList()
            updatedHistory.removeAll { it.id == product.id }
            updatedHistory.add(0, product)
            updatedHistory
        }
    }
}