package com.example.animalsupermarket.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageRepository @Inject constructor() {
    private val _language = MutableStateFlow("en")
    val language: StateFlow<String> = _language

    fun setLanguage(language: String) {
        _language.value = language
    }
}