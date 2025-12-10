package org.crow.viewModel

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
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


class GameDetailScreenViewModel(
    private val id: Long,
    private val dataRepository: DataRepository,
    private val preferencesRepo: AppPreferencesRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<GameUiState> = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            preferencesRepo.userToken.collect{
                if(it.isNullOrEmpty()) updateUiState(loggedIn = false) else updateUiState(loggedIn = true)
            }
        }
        getGamesSet()
    }

    private fun getGamesSet() {
        var gameDetail:ApiResponse<GameDto>
        var reviews:ApiResponse<Set<ReviewCrowGames>>
        viewModelScope.launch {
            try {
                gameDetail = dataRepository.getGame(id = id)
                reviews= dataRepository.getReviewsGame(id)
                println(reviews)
                updateUiState(
                    gameDetail = gameDetail.data?:GameDto(),
                    loading = false,
                    reviews = reviews.data?: emptySet()
                )
            } catch (e: Exception) {
                updateUiState(
                    loading = true
                )
            }
        }
    }

    fun clickTab(index: Int) {
        updateUiState(selectedTabIndex = index)
    }

    fun updateRating(rating: Int) {
        viewModelScope.launch {
            if(preferencesRepo.userToken.first().isNullOrEmpty()) {
            }else{
                dataRepository.postRating(rating, id, checkNotNull(preferencesRepo.userToken.first()))
                getGamesSet()
            }
        }
    }

    fun updateTopBarState(isSolid: Boolean) {
        updateUiState(
            isTopBarSolid = isSolid,
            topBarColor = if (isSolid) {
                Color(0xFF000000)
            } else {
                Color.Transparent
            }
        )
    }

    private fun updateUiState(
        gameDetail: GameDto = _uiState.value.gameDetail,
        loading: Boolean = _uiState.value.loading,
        expandedText: Boolean = _uiState.value.expandedText,
        selectedTabIndex: Int = _uiState.value.selectedTabIndex,
        isTopBarSolid: Boolean = _uiState.value.isTopBarSolid,
        topBarColor: Color = _uiState.value.topBarColor,
        reviews: Set<ReviewCrowGames> = _uiState.value.reviews,
        loggedIn: Boolean=_uiState.value.loggedIn
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                gameDetail = gameDetail,
                loading = loading,
                expandedText = expandedText,
                selectedTabIndex = selectedTabIndex,
                isTopBarSolid = isTopBarSolid,
                topBarColor = topBarColor,
                reviews = reviews,
                loggedIn = loggedIn
            )
        }
    }

    fun updateExpandedText() {
        _uiState.update { currentState ->
            currentState.copy(expandedText = !_uiState.value.expandedText)
        }
    }

    data class GameUiState(
        val gameDetail: GameDto = GameDto(),
        val loading: Boolean = true,
        val tabList: List<String>? = listOf("Category", "Details", "Covers", "Requirements", "DLC"),
        val expandedText: Boolean = false,
        val selectedTabIndex: Int = 0,
        val isTopBarSolid: Boolean = false,
        val topBarColor: Color = Color.Transparent,
        val reviews:Set<ReviewCrowGames> = emptySet(),
        val loggedIn:Boolean = false,
    )
}





