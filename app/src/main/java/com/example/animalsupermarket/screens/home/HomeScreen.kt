package com.example.animalsupermarket.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.animalsupermarket.model.Product
import androidx.navigation.NavController
import com.example.animalsupermarket.screens.components.Carousel
import com.example.animalsupermarket.viewmodel.HomeViewModel
import com.example.animalsupermarket.viewmodel.SortBy
import com.example.animalsupermarket.viewmodel.FavoritesViewModel
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel, favoritesViewModel: FavoritesViewModel = viewModel()) {
    val products by homeViewModel.filteredProducts.collectAsState()
    val categories = homeViewModel.categories
    var selectedCategory by remember { mutableStateOf("全部") }
    var searchQuery by remember { mutableStateOf("") }
    val carouselImages = listOf(
        "https://img.alicdn.com/imgextra/i4/2200748495329/O1CN01b6A3b61sY2Zz4Zz3j_!!2200748495329.jpg",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fgd1.alicdn.com%2Fimgextra%2Fi1%2F2200748495329%2FO1CN01b6A3b61sY2Zz4Zz3j_!!2200748495329.jpg&refer=http%3A%2F%2Fgd1.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1678906800&t=d8b8b8b8b8b8b8b8b8b8b8b8b8b8b8b8",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fgd2.alicdn.com%2Fimgextra%2Fi2%2F2200748495329%2FO1CN01b6A3b61sY2Zz4Zz3j_!!2200748495329.jpg&refer=http%3A%2F%2Fgd2.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1678906800&t=d8b8b8b8b8b8b8b8b8b8b8b8b8b8b8b8"
    )

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            query = searchQuery,
            onQueryChange = { homeViewModel.onSearchQueryChange(it) },
            onSearch = { homeViewModel.onSearchQueryChange(searchQuery) }
        )
        Carousel(images = carouselImages)
        CategoryGrid(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = { category ->
                navController.navigate("category/$category")
            }
        )
        SortOptions { sortBy ->
            homeViewModel.sortProducts(sortBy)
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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

@Composable
fun ProductCard(product: Product, favoritesViewModel: FavoritesViewModel, onClick: () -> Unit) {
    val isFavorite by favoritesViewModel.isFavoriteFlow(product.id).collectAsState(initial = false)

    Card(
        modifier = Modifier
            .clickable(onClick = onClick)
            .width(180.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Box(modifier = Modifier.height(180.dp)) {
                Image(
                    painter = rememberAsyncImagePainter(product.imageUrl),
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = { favoritesViewModel.toggleFavorite(product) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color(0xFFFF4081) else Color.White
                    )
                }
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "¥${product.price}",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFFFF5722)
                    )
                    Text(
                        text = "${product.sales}人付款",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit, onSearch: () -> Unit) {
    var text by remember { mutableStateOf(query) }
    TextField(
        value = text,
        onValueChange = {
            text = it
            onQueryChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = { Text("搜索商品") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
        singleLine = true,
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardActions = KeyboardActions(onSearch = { onSearch() })
    )
}
@Composable
fun CategoryGrid(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categories) { category ->
            CategoryItem(
                category = category,
                isSelected = category == selectedCategory,
                onCategorySelected = onCategorySelected
            )
        }
    }
}

@Composable
fun SortOptions(onSortSelected: (SortBy) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(onClick = { onSortSelected(SortBy.None) }) {
            Text("默认")
        }
        Button(onClick = { onSortSelected(SortBy.PriceAsc) }) {
            Text("价格升序")
        }
        Button(onClick = { onSortSelected(SortBy.PriceDesc) }) {
            Text("价格降序")
        }
        Button(onClick = { onSortSelected(SortBy.Sales) }) {
            Text("销量")
        }
    }
}

@Composable
fun CategoryItem(
    category: String,
    isSelected: Boolean,
    onCategorySelected: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable { onCategorySelected(category) }
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            modifier = Modifier.size(56.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Pets,
                contentDescription = category,
                tint = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = category,
            style = MaterialTheme.typography.bodySmall,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}