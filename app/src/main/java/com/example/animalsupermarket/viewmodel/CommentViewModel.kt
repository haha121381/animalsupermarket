package com.example.animalsupermarket.viewmodel

import androidx.lifecycle.ViewModel
import com.example.animalsupermarket.model.Comment
import com.example.animalsupermarket.repository.getSampleComments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor() : ViewModel() {

    private val _comments = MutableStateFlow<Map<Int, List<Comment>>>(emptyMap())
    val comments: StateFlow<Map<Int, List<Comment>>> = _comments.asStateFlow()

    init {
        _comments.value = getSampleComments()
    }

    fun getCommentsForProduct(productId: Int): List<Comment> {
        return _comments.value[productId] ?: emptyList()
    }

    fun addComment(productId: Int, userName: String, userAvatarUrl: String, rating: Int, text: String) {
        val newComment = Comment(
            id = UUID.randomUUID().hashCode(),
            productId = productId,
            userName = userName,
            userAvatarUrl = userAvatarUrl,
            rating = rating,
            text = text,
            date = Date()
        )
        _comments.update { currentComments ->
            val productComments = currentComments[productId] ?: emptyList()
            currentComments + (productId to (productComments + newComment))
        }
    }
}