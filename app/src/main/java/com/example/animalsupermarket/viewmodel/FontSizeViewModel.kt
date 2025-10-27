package com.example.animalsupermarket.viewmodel

import androidx.lifecycle.ViewModel
import com.example.animalsupermarket.repository.FontSizeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FontSizeViewModel @Inject constructor(private val repository: FontSizeRepository) : ViewModel() {
    val fontSize: StateFlow<Float> = repository.fontSize

    fun setFontSize(fontSize: Float) {
        repository.setFontSize(fontSize)
    }
}