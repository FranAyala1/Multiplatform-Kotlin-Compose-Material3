package org.crow.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.network.sockets.ConnectTimeoutException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.crow.data.AppPreferencesRepository
import org.crow.data.DataRepository
import org.crow.model.ApiResponse
import org.crow.model.Game.GameDto
import org.crow.model.ReviewCrowGames
import org.crow.model.UserDetailsDto
import org.crow.viewModel.GameDetailScreenViewModel.GameUiState

class UserScreenViewModel(
    private val dataRepository: DataRepository,
    private val loginDataStore: AppPreferencesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<ScreenUiState> = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            loginDataStore.userToken.collect{
                if(it.isNullOrEmpty()) logOut()  else getUser()
            }
        }

    }

    private fun getUser() {
        var userDetails: ApiResponse<UserDetailsDto>
        var reviews:ApiResponse<Set<ReviewCrowGames>>
        viewModelScope.launch {
            try {
                userDetails = dataRepository.getUser(checkNotNull(loginDataStore.userToken.first()))
                reviews= dataRepository.getReviewsUser(checkNotNull(loginDataStore.userToken.first()))
                println(reviews)
                updateUiState(
                    userDetails = checkNotNull(userDetails.data),
                    reviews = reviews.data?: emptySet(),
                    loading = false
                )
            } catch (e: ConnectTimeoutException) {
                updateUiState(loading = false)
            } catch (e: Exception) {
                updateUiState(loading = false)
            }
        }
    }

    fun logOut(){
        viewModelScope.launch {
            loginDataStore.clearToken()
            reset()
        }
    }

    private fun reset(){
        updateUiState(UserDetailsDto())
    }

    private fun updateUiState(
        userDetails: UserDetailsDto=_uiState.value.userDetailsDto,
        loggedIn: Boolean = _uiState.value.loggedIn,
        reviews: Set<ReviewCrowGames> = _uiState.value.reviews,
        loading:Boolean=_uiState.value.loading
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                userDetailsDto = userDetails,
                loggedIn=loggedIn,
                reviews=reviews,
                loading = loading
            )
        }
    }

    data class ScreenUiState(
        val userDetailsDto: UserDetailsDto = UserDetailsDto(),
        val loggedIn: Boolean = false,
        val reviews:Set<ReviewCrowGames> = emptySet(),
        val loading:Boolean=true
    )
}