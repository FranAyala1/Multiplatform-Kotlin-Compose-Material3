package org.crow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.crow.data.AppPreferencesRepository
import org.crow.data.DataRepository
import org.crow.model.ReviewCrowGames
import org.crow.model.UserDetailsDto
import org.crow.viewModel.UserScreenViewModel.ScreenUiState

class ReviewScreenViewModel(
    private val idGame:Long,
    private val dataRepository: DataRepository,
    private val preferencesRepo: AppPreferencesRepository,
    private val onResetToDetailGame: () -> Unit
) : ViewModel() {

    private val _uiState: MutableStateFlow<ScreenUiState> = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    fun sendReview() {
        viewModelScope.launch {
            dataRepository.postRating(rating = _uiState.value.rating, gameId = idGame,preferencesRepo.userToken.first()?:"")
            dataRepository.postReview(contenido = _uiState.value.reviewText, gameId = idGame,preferencesRepo.userToken.first()?:"")
        }
        onResetToDetailGame()
    }

    fun updateRating(rating: Int) {
        updateUiState(rating=rating)
    }

    fun updateReviewText(
        reviewText: String,
        characterCount: Int
    ) {
        updateUiState(reviewText = reviewText, characterCount = characterCount)
    }

    private fun updateUiState(
        rating: Int = _uiState.value.rating,
        reviewText: String = _uiState.value.reviewText,
        characterCount: Int = _uiState.value.characterCount,
        maxCharacters: Int = _uiState.value.maxCharacters
    ) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    rating = rating,
                    reviewText = reviewText,
                    characterCount=characterCount,
                    maxCharacters=maxCharacters
                )
            }
        }
    }
    data class ScreenUiState(
        val rating: Int = 0,
        val reviewText: String = "",
        val characterCount: Int = 0,
        val maxCharacters: Int = 500
    )
}