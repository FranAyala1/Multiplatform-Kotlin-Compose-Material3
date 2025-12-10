package org.crow.viewModel

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
import org.crow.viewModel.UserScreenViewModel.ScreenUiState

class ReviewGamesScreenViewModel(
    private val idGame: Long,
    private val dataRepository: DataRepository,
    private val preferencesRepository: AppPreferencesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<ScreenUiState> = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    init {
        getGamesSet()
    }

    private fun getGamesSet() {
        var reviews:ApiResponse<Set<ReviewCrowGames>>
        viewModelScope.launch {
            try {
                reviews= dataRepository.getReviewsGame(idGame)
                updateUiState(
                    reviews = reviews.data?: emptySet(),
                    loading = false,
                )
            } catch (e: Exception) {
                updateUiState(
                    loading = true
                )
            }
        }
    }

    private fun updateUiState(
        reviews: Set<ReviewCrowGames> = _uiState.value.reviews,
        loading: Boolean = _uiState.value.loading,
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                reviews = reviews,
                loading = loading,
            )
        }
    }
    data class ScreenUiState(
        val reviews: Set<ReviewCrowGames> = emptySet(),
        val loading: Boolean = true
    )
}