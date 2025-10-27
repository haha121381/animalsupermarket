package com.example.animalsupermarket.viewmodel

import androidx.lifecycle.ViewModel
import com.example.animalsupermarket.repository.LanguageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(private val repository: LanguageRepository) : ViewModel() {
    val language: StateFlow<String> = repository.language

    fun setLanguage(language: String) {
        repository.setLanguage(language)
    }
}