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

enum class SortBy {
    None,
    PriceAsc,
    PriceDesc,
    Sales
}

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ProductRepository) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _filteredProducts = MutableStateFlow<List<Product>>(emptyList())
    val filteredProducts: StateFlow<List<Product>> = _filteredProducts

    val categories: List<String>
        get() = listOf("全部") + _products.value.map { it.category }.distinct()

    private val _searchHistory = MutableStateFlow<List<String>>(emptyList())
    val searchHistory: StateFlow<List<String>> = _searchHistory

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            val productList = repository.getProducts()
            _products.value = productList
            _filteredProducts.value = productList
        }
    }

    fun addSearchQuery(query: String) {
        if (query.isNotBlank() && !_searchHistory.value.contains(query)) {
            _searchHistory.value = listOf(query) + _searchHistory.value
        }
    }

    fun clearSearchHistory() {
        _searchHistory.value = emptyList()
    }

    fun filterProducts(category: String) {
        if (category == "全部") {
            _filteredProducts.value = _products.value
        } else {
            _filteredProducts.value = _products.value.filter { it.category == category }
        }
    }

    fun sortProducts(sortBy: SortBy) {
        when (sortBy) {
            SortBy.PriceAsc -> _filteredProducts.value = _filteredProducts.value.sortedBy { it.price }
            SortBy.PriceDesc -> _filteredProducts.value = _filteredProducts.value.sortedByDescending { it.price }
            SortBy.Sales -> _filteredProducts.value = _filteredProducts.value.sortedByDescending { it.sales }
            SortBy.None -> {
                // Do nothing
            }
        }
    }
    }

    fun onSearchQueryChange(query: String) {
        _filteredProducts.value = _products.value.filter {
            it.name.contains(query, ignoreCase = true) ||
            it.category.contains(query, ignoreCase = true)
        }
    }
}