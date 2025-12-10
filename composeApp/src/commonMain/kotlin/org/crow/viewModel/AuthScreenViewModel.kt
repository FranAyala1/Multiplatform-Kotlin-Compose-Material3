package org.crow.viewModel

import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil3.Image
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.crow.data.AppPreferencesRepository
import org.crow.data.DataRepository
import org.crow.data.createDataStore
import org.crow.model.ApiResponse
import org.crow.model.Game.AuthResponse
import org.crow.model.RegisterRequest
import org.crow.model.UserDto


class AuthScreenViewModel(
    private val dataRepository: DataRepository = DataRepository(),
    private val preferencesRepo: AppPreferencesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<GameUiState> = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private fun updateUiState(
        login: Login = _uiState.value.login,
        register: Register = _uiState.value.register,
        selectedTab: Int = _uiState.value.selectedTab,
        isLoged: Boolean=_uiState.value.isLoged
    ) {
        _uiState.update { currentState ->
            currentState.copy(login = login, register = register, selectedTab = selectedTab, isLoged = isLoged)
        }
    }

    fun tabSelected(index: Int) {
        updateUiState(selectedTab = index)
    }

    fun loginUpdate(
        username: String = _uiState.value.login.username,
        password: String = _uiState.value.login.password,
        error: Error = _uiState.value.login.error
    ) {
        updateUiState(login = Login(username = username, password = password, error = error))
    }

    fun login() {
        var error: Error
        var apiResponse: ApiResponse<AuthResponse>
        if (_uiState.value.login.username.isBlank() || _uiState.value.login.password.isBlank()) {
            loginUpdate(
                error = Error(
                    response = true,
                    message = "Username or password can't be empty"
                )
            )
        } else {
            viewModelScope.launch {
                apiResponse = dataRepository.login(
                    UserDto(
                        username = _uiState.value.login.username,
                        password = _uiState.value.login.password
                    )
                )
                if (apiResponse.status.equals("error")) {
                    error = Error(response = true, message = apiResponse.message ?: "")
                    loginUpdate(error = error)
                } else {
                    preferencesRepo.saveUserToken(checkNotNull(apiResponse.data?.token))
                }
            }
        }
    }

    private fun registerUpdate(
        username: String = _uiState.value.register.username,
        email: String = _uiState.value.register.email,
        password: String = _uiState.value.register.password,
        image: Image? = _uiState.value.register.image,
        error: Error = _uiState.value.register.error,
    ) {
        updateUiState(
            register = Register(
                username = username,
                email = email,
                password = password,
                image = image,
                error = error,
            )
        )
    }

    fun onRegisterUsernameChange(
        username: String,
    ) {
        registerUpdate(username = username)

    }

    fun onRegisterEmailChange(
        email: String,
    ) {
        registerUpdate(email = email)
    }

    fun onRegisterPasswordChange(
        password: String,
    ) {
        registerUpdate(password = password)
    }

    fun register() {
        var newError: Error
        var apiResponse: ApiResponse<AuthResponse>
        viewModelScope.launch {
            if (_uiState.value.register.username.isBlank()) {
                newError = Error(message = "Invalid Username", response = true)
                registerUpdate(error = newError)
            } else {
                apiResponse = dataRepository.register(
                    RegisterRequest(
                        username = _uiState.value.register.username,
                        email = _uiState.value.register.email,
                        password = _uiState.value.register.username
                    )
                )
                if(apiResponse.status=="error"){
                    newError = Error(message = apiResponse.message?:"", response = true)
                    registerUpdate(error = newError)
                }else{
                    preferencesRepo.saveUserToken(checkNotNull(apiResponse.data?.token))
                }
            }
        }
    }

    data class GameUiState(
        val login: Login = Login(),
        val register: Register = Register(),
        val tabs: List<String> = listOf("Login", "Register"),
        val selectedTab: Int = 0,
        val isLoged:Boolean=false
    )

    data class Login(
        val username: String = "",
        val password: String = "",
        val error: Error = Error()
    )

    data class Register(
        val username: String = "",
        val email: String = "",
        val password: String = "",
        val image: Image? = null,
        val error: Error = Error()
    )

    data class Error(
        val response: Boolean = false,
        val message: String = "",
    )
}

fun esCorreo(texto: String): Boolean {
    return texto.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$".toRegex())
}