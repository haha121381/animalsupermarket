package com.example.animalsupermarket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalsupermarket.model.Product
import com.example.animalsupermarket.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: ProductRepository) : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _searchHistory = MutableStateFlow<List<String>>(emptyList())
    val searchHistory: StateFlow<List<String>> = _searchHistory

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _products.value = repository.getProducts()
        }
    }

    fun addSearchQuery(query: String) {
        if (query.isNotBlank() && !_searchHistory.value.contains(query)) {
            _searchHistory.value = _searchHistory.value + query
        }
    }

    fun clearSearchHistory() {
        _searchHistory.value = emptyList()
    }
}