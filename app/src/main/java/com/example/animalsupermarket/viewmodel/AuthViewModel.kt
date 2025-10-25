package com.example.animalsupermarket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavController
import com.example.animalsupermarket.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class AuthState(
    val isLoggedIn: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class AuthViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _navigationEvent = Channel<NavigationEvent>()
    val navigationEvent = _navigationEvent.receiveAsFlow()

    fun login(email: String, pass: String) {
        if (email.isNotEmpty() && pass.isNotEmpty()) {
            _authState.update { it.copy(isLoggedIn = true, error = null) }
        } else {
            val error = getApplication<Application>().getString(R.string.error_email_password_empty)
            _authState.update { it.copy(error = error) }
        }
    }

    fun register(name: String, email: String, pass: String, confirmPass: String) {
        if (pass != confirmPass) {
            val error = getApplication<Application>().getString(R.string.error_password_mismatch)
            _authState.update { it.copy(error = error) }
            return
        }
        if (name.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty()) {
            _authState.update { it.copy(isLoggedIn = true, error = null) }
        } else {
            val error = getApplication<Application>().getString(R.string.error_all_fields_required)
            _authState.update { it.copy(error = error) }
        }
    }

    fun logout() {
        _authState.update { it.copy(isLoggedIn = false) }
        _navigationEvent.trySend(NavigationEvent.NavigateToHome)
    }

}

sealed class NavigationEvent {
    object NavigateToHome : NavigationEvent()
}