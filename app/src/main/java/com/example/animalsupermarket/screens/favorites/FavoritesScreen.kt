package com.example.animalsupermarket.screens.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.animalsupermarket.screens.category.ProductCard
import com.example.animalsupermarket.viewmodel.FavoritesViewModel
import androidx.navigation.NavController
import com.example.animalsupermarket.viewmodel.CartViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FavoritesScreen(favoritesViewModel: FavoritesViewModel, navController: NavController) {
    val favorites by favoritesViewModel.favorites.collectAsState()
    val cartViewModel: CartViewModel = hiltViewModel()

    if (favorites.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("你还没有收藏任何商品")
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(favorites) { product ->
                ProductCard(
                    product = product,
                    favoritesViewModel = favoritesViewModel,
                    onClick = {
                        navController.navigate("productDetails/${product.id}")
                    },
                    cartViewModel = cartViewModel
                )
            }
        }
    }
}