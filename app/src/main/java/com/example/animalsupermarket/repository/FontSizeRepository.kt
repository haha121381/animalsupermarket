package com.example.animalsupermarket.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FontSizeRepository @Inject constructor() {
    private val _fontSize = MutableStateFlow(16f)
    val fontSize: StateFlow<Float> = _fontSize

    fun setFontSize(fontSize: Float) {
        _fontSize.value = fontSize
    }
}