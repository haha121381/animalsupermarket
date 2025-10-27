package com.example.animalsupermarket.screens.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.animalsupermarket.screens.category.ProductCard
import com.example.animalsupermarket.viewmodel.FavoritesViewModel
import com.example.animalsupermarket.viewmodel.HistoryViewModel
import com.example.animalsupermarket.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    navController: NavController,
    historyViewModel: HistoryViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    val history by historyViewModel.history.collectAsState()
    val cartViewModel: CartViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("历史足迹") }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(history) { product ->
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