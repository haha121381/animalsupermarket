package com.example.animalsupermarket.viewmodel

import androidx.lifecycle.ViewModel
import com.example.animalsupermarket.repository.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(private val repository: ThemeRepository) : ViewModel() {
    val isDarkTheme: StateFlow<Boolean> = repository.isDarkTheme

    fun setTheme(isDarkTheme: Boolean) {
        repository.setTheme(isDarkTheme)
    }
}