package com.example.animalsupermarket.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.animalsupermarket.R
import com.example.animalsupermarket.model.Product
import com.example.animalsupermarket.ui.theme.IconColor
import com.example.animalsupermarket.ui.theme.Primary
import com.example.animalsupermarket.ui.theme.TextColorSecondary
import com.example.animalsupermarket.viewmodel.FavoritesViewModel
import com.example.animalsupermarket.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel,
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    val products by homeViewModel.filteredProducts.collectAsState()
    val carouselImages = listOf(
        "https://example.com/promo1.png",
        "https://example.com/promo2.png",
        "https://example.com/promo3.png"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        item {
            Header(navController)
        }
        item {
            Carousel(images = carouselImages)
        }
        item {
            CategoryIcons(navController)
        }
        item {
            ServiceGuarantees()
        }
        item {
            HotRecommendations(navController, products, favoritesViewModel)
        }
    }
}

@Composable
fun Header(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "宠物用品大超市",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("搜索宠物用品") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            modifier = Modifier.width(200.dp),
            shape = RoundedCornerShape(20.dp)
        )
    }
}

@Composable
fun CategoryIcons(navController: NavController) {
    val categories = listOf("狗粮", "猫粮", "玩具", "用品", "医疗", "美容")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        categories.forEach { category ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { navController.navigate("category/$category") }
            ) {
                Icon(Icons.Default.Pets, contentDescription = category, modifier = Modifier.size(40.dp))
                Text(category, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun ServiceGuarantees() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ServiceItem(icon = Icons.Default.Timer, text = "限时抢购")
        ServiceItem(icon = Icons.Default.LocalShipping, text = "次日达")
        ServiceItem(icon = Icons.Default.Verified, text = "品质保证")
        ServiceItem(icon = Icons.Default.HeadsetMic, text = "在线客服")
    }
}

@Composable
fun ServiceItem(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            icon,
            contentDescription = text,
            tint = Primary,
            modifier = Modifier
                .size(48.dp)
                .background(Primary.copy(alpha = 0.1f), CircleShape)
                .padding(12.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun HotRecommendations(
    navController: NavController,
    products: List<Product>,
    favoritesViewModel: FavoritesViewModel
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("热门推荐", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            TextButton(onClick = { /* TODO */ }) {
                Text("更多 >", color = TextColorSecondary)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.height(500.dp), // Avoid nesting scrollable components
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products) { product ->
                ProductCard(
                    product = product,
                    favoritesViewModel = favoritesViewModel,
                    onClick = { navController.navigate("productDetails/${product.id}") }
                )
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, favoritesViewModel: FavoritesViewModel, onClick: () -> Unit) {
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
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    product.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "¥${product.price}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Primary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}