package com.example.animalsupermarket.screens.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.animalsupermarket.screens.home.ProductCard
import com.example.animalsupermarket.viewmodel.HomeViewModel
import com.example.animalsupermarket.viewmodel.FavoritesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    navController: NavController,
    category: String,
    homeViewModel: HomeViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    val products by homeViewModel.products.collectAsState()
    val filteredProducts = products.filter { it.category == category }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(category) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(filteredProducts) { product ->
                ProductCard(
                    product = product,
                    favoritesViewModel = favoritesViewModel,
                    onClick = {
                        navController.navigate("productDetails/${product.id}")
                    }
                )
            }
        }
    }
}