package com.example.animalsupermarket.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.animalsupermarket.screens.home.ProductCard
import com.example.animalsupermarket.viewmodel.HomeViewModel
import com.example.animalsupermarket.viewmodel.FavoritesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, homeViewModel: HomeViewModel = viewModel(), favoritesViewModel: FavoritesViewModel = viewModel()) {
    var searchQuery by remember { mutableStateOf("") }
    val products by homeViewModel.products.collectAsState()
    val searchHistory by homeViewModel.searchHistory.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("搜索") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues -> 
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("搜索商品") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    homeViewModel.addSearchQuery(searchQuery)
                })
            )

            if (searchQuery.isBlank()) {
                SearchHistory(
                    history = searchHistory,
                    onClearHistory = { homeViewModel.clearSearchHistory() },
                    onHistoryClick = { query ->
                        searchQuery = query
                        homeViewModel.addSearchQuery(query)
                    }
                )
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(products.filter { it.name.contains(searchQuery, ignoreCase = true) }) { product ->
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
    }
}

@Composable
fun SearchHistory(
    history: List<String>,
    onClearHistory: () -> Unit,
    onHistoryClick: (String) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("搜索历史", style = MaterialTheme.typography.titleMedium)
            IconButton(onClick = onClearHistory) {
                Icon(Icons.Default.Clear, contentDescription = "Clear History")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(history) { query ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onHistoryClick(query) }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(androidx.compose.material.icons.filled.History, contentDescription = "History Icon")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(query)
                }
            }
        }
    }
}