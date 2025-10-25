package com.example.animalsupermarket.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.animalsupermarket.model.Product
import com.example.animalsupermarket.viewmodel.CartViewModel
import com.example.animalsupermarket.viewmodel.FavoritesViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animalsupermarket.viewmodel.CommentViewModel
import com.example.animalsupermarket.viewmodel.ProductDetailsViewModel
import com.example.animalsupermarket.viewmodel.HistoryViewModel
import java.text.SimpleDateFormat
import java.util.Locale
import androidx.compose.runtime.LaunchedEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    navController: NavController,
    productId: Int,
    productDetailsViewModel: ProductDetailsViewModel,
    cartViewModel: CartViewModel,
    favoritesViewModel: FavoritesViewModel,
    historyViewModel: HistoryViewModel = viewModel()
) {
    LaunchedEffect(productId) {
        productDetailsViewModel.getProductById(productId)
        productDetailsViewModel.product.value?.let { historyViewModel.addToHistory(it) }
    }

    val product by productDetailsViewModel.product.collectAsState()

    if (product == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("商品不存在")
        }
        return
    }

    val isFavorite = favoritesViewModel.isFavorite(product!!)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(product!!.name) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = rememberAsyncImagePainter(product!!.imageUrl),
                        contentDescription = product!!.name,
                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                    IconButton(
                        onClick = { favoritesViewModel.toggleFavorite(product!!) },
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) MaterialTheme.colorScheme.primary else Color.Gray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(text = product!!.name, style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = product!!.description, style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "¥${product!!.price}", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
                    Text(text = "月销 ${product!!.monthlySales}", style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.height(16.dp))
                val cartItem = cartViewModel.cartItems.collectAsState().value.find { it.product.id == product!!.id }
                if (cartItem == null) {
                    Button(
                        onClick = { cartViewModel.addToCart(product!!) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text("添加到购物车")
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { cartViewModel.decreaseQuantity(product!!.id) }) {
                            Icon(Icons.Default.Remove, contentDescription = "Decrease")
                        }
                        Text(cartItem.quantity.toString(), style = MaterialTheme.typography.headlineSmall)
                        IconButton(onClick = { cartViewModel.increaseQuantity(product!!.id) }) {
                            Icon(Icons.Default.Add, contentDescription = "Increase")
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider()
                CommentSection(productId = product!!.id, navController = navController)
            }
        }
    }
}

@Composable
fun CommentSection(productId: Int, navController: NavController, commentViewModel: CommentViewModel = viewModel()) {
    val comments = commentViewModel.getCommentsForProduct(productId)

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("用户评价 (${comments.size})", style = MaterialTheme.typography.headlineSmall)
            Button(onClick = { navController.navigate("comments/$productId") }) {
                Text("查看全部")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (comments.isEmpty()) {
            Text("暂无评价")
        } else {
            LazyColumn(modifier = Modifier.height(200.dp)) {
                items(comments.take(3)) { comment ->
                    CommentItem(comment = comment)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun CommentItem(comment: com.example.animalsupermarket.model.Comment) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(comment.userAvatarUrl),
                contentDescription = comment.userName,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(comment.userName, fontWeight = FontWeight.Bold)
                Row {
                    repeat(comment.rating) {
                        Icon(Icons.Default.Star, contentDescription = "Star", tint = Color(0xFFFFC107))
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(comment.text)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(comment.date),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}