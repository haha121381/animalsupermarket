package com.example.animalsupermarket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalsupermarket.model.User
import com.example.animalsupermarket.repository.CouponsRepository
import com.example.animalsupermarket.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val couponsRepository: CouponsRepository
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    fun fetchUser() {
        viewModelScope.launch {
            _user.value = userRepository.getUser()
        }
    }

    fun login(name: String, email: String) {
        _user.value = User(name, email, "https://example.com/avatar.png", 0, couponsRepository.getCoupons().size)
    }

    fun clearProfile() {
        _user.value = null
    }
}