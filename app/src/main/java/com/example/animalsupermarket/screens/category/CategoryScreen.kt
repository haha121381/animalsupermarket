package com.example.animalsupermarket.screens.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FilterList
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.animalsupermarket.model.Product
import com.example.animalsupermarket.ui.theme.CardBackground
import com.example.animalsupermarket.ui.theme.IconColor
import com.example.animalsupermarket.ui.theme.Primary
import com.example.animalsupermarket.ui.theme.TextColorSecondary
import com.example.animalsupermarket.viewmodel.CartViewModel
import com.example.animalsupermarket.viewmodel.CategoryViewModel
import com.example.animalsupermarket.viewmodel.FavoritesViewModel
import com.example.animalsupermarket.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    navController: NavController,
    categoryViewModel: CategoryViewModel,
    cartViewModel: CartViewModel,
    favoritesViewModel: FavoritesViewModel
) {
    var selectedCategory by remember { mutableStateOf("狗粮") }
    val categories = listOf("狗粮", "猫粮", "玩具", "用品", "医疗", "美容")
    val products by categoryViewModel.products.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("商品分类") },
                actions = {
                    IconButton(onClick = { /* TODO: Search */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Background)
            )
        },
        containerColor = Background
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CategorySidebar(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it }
            )
            ProductGrid(
                navController = navController,
                products = products,
                favoritesViewModel = favoritesViewModel,
                selectedCategory = selectedCategory,
                cartViewModel = cartViewModel
            )
        }
    }
}

@Composable
fun CategorySidebar(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .width(100.dp)
            .background(Color.White)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(categories) { category ->
            CategoryItem(
                category = category,
                isSelected = category == selectedCategory,
                onSelected = { onCategorySelected(category) }
            )
        }
    }
}

@Composable
fun CategoryItem(category: String, isSelected: Boolean, onSelected: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onSelected)
            .padding(vertical = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(if (isSelected) Primary.copy(alpha = 0.1f) else Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            // Replace with actual icons
            Icon(
                Icons.Default.Pets,
                contentDescription = category,
                tint = if (isSelected) Primary else IconColor
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = category,
            color = if (isSelected) Primary else TextColor,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun ProductGrid(
    navController: NavController,
    products: List<Product>,
    favoritesViewModel: FavoritesViewModel,
    selectedCategory: String,
    cartViewModel: CartViewModel
) {
    val filteredProducts = products.filter { it.category == selectedCategory }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "狗粮",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                "共${filteredProducts.size}件商品",
                style = MaterialTheme.typography.bodySmall,
                color = TextColorSecondary
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        FilterAndSort()
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredProducts) { product ->
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

@Composable
fun FilterAndSort() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text("综合排序", fontWeight = FontWeight.Bold, color = Primary)
        Text("价格")
        Text("销量")
        Row {
            Text("筛选")
            Icon(Icons.AutoMirrored.Filled.FilterList, contentDescription = "Filter")
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    favoritesViewModel: FavoritesViewModel,
    onClick: () -> Unit,
    cartViewModel: CartViewModel
) {
    val isFavorite by favoritesViewModel.isFavoriteFlow(product.id).collectAsState(initial = false)

    Card(
        modifier = Modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(product.imageUrl),
                contentDescription = product.name,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    product.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        "热销",
                        color = Color.Red,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .background(Color.Red.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    )
                    Text(
                        "包邮",
                        color = Primary,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .background(Primary.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "¥${product.price}",
                        style = MaterialTheme.typography.titleMedium,
                        color = Primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "¥399",
                        textDecoration = TextDecoration.LineThrough,
                        color = TextColorSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color.Yellow,
                            modifier = Modifier.size(16.dp)
                        )
                        Text("4.8", style = MaterialTheme.typography.bodySmall, color = TextColorSecondary)
                    }
                    Text("已售890件", style = MaterialTheme.typography.bodySmall, color = TextColorSecondary)
                }
            }
        }
    }
}