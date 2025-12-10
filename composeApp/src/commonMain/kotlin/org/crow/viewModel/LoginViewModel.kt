package org.crow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.crow.data.AppPreferencesRepository
import org.crow.data.DataRepository


class LoginViewModel(
    private val loginDataStore: AppPreferencesRepository,
    private val dataRepository: DataRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<GameUiState> = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            loginDataStore.userToken.collect{
               if(it.isNullOrEmpty()) updateUiState(loggedIn = false) else updateUiState(loggedIn = true)
            }
        }
    }

    private fun updateUiState(
        loggedIn: Boolean = _uiState.value.loggedIn,
    ) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(loggedIn = loggedIn)
            }
        }
    }

    fun performLogin(username: String, password: String) {
        viewModelScope.launch {
            val token = "received.jwt.token"
            loginDataStore.saveUserToken(token)
        }
    }

    fun logout() {
        viewModelScope.launch {
            loginDataStore.clearToken()
        }
    }
    data class GameUiState(
        val loggedIn: Boolean = false,
    )
}

