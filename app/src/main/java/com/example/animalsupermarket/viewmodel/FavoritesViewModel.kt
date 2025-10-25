package com.example.animalsupermarket.viewmodel

import androidx.lifecycle.ViewModel
import com.example.animalsupermarket.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor() : ViewModel() {

    private val _favorites = MutableStateFlow<List<Product>>(emptyList())
    val favorites: StateFlow<List<Product>> = _favorites.asStateFlow()

    fun isFavorite(product: Product): Boolean {
        return _favorites.value.any { it.id == product.id }
    }

    fun isFavoriteFlow(productId: Int): Flow<Boolean> {
        return favorites.map { favorites ->
            favorites.any { it.id == productId }
        }
    }

    fun toggleFavorite(product: Product) {
        _favorites.update { currentFavorites ->
            if (isFavorite(product)) {
                currentFavorites.filterNot { it.id == product.id }
            } else {
                currentFavorites + product
            }
        }
    }
}