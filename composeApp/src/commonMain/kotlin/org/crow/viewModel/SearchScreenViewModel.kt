package org.crow.viewModel

import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import io.ktor.client.network.sockets.ConnectTimeoutException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.crow.data.DataRepository
import org.crow.model.Game.GameDto
import org.crow.model.ReviewCrowGames

class SearchScreenViewModel(private val dataRepository: DataRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<ScreenUiState> = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private fun findGames() {
        viewModelScope.launch {
            try {
                println(_uiState.value.filter)
                when(_uiState.value.filter){
                    "Games" ->{ val response = dataRepository.getGamesFromSearchAndFilter(
                        search = _uiState.value.search, size = _uiState.value.pageSize, page =0
                    )
                        updateUiState(
                            resultGame = checkNotNull(response.data?.content),
                            loading = false,
                            currentPage = 1,
                            hasMoreData = response.data?.isLast != true
                        )
                    }
                    "Reviews" ->{
                        println(_uiState.value.resultReview)
                        val response = dataRepository.getReviewsFromSearchAndFilter(
                            search = _uiState.value.search
                        )
                        updateUiState(
                            resultReview = checkNotNull(response.data),
                            loading = false,
                        )
                    }
                }
                println(_uiState.value.resultReview.elementAt(0))

            } catch (e: ConnectTimeoutException) {
                updateUiState(loading = false, error = "Timeout error")
            } catch (e: Exception) {
                updateUiState(loading = false, error = "Error loading games: ${e.message}")
            }
        }
    }

    fun updateImages() {
        if (_uiState.value.loadedImages % _uiState.value.pageSize < 3) {
            updateUiState(isLoaded = true)
        }
        updateUiState(loadedImages = _uiState.value.loadedImages + 1)

    }

    fun loadMoreGames() {
        if (_uiState.value.isLoadingMore || !_uiState.value.hasMoreData) return
        updateUiState(loadingMore = true)
        viewModelScope.launch {
            try {
                val response = dataRepository.getGames(
                    page = _uiState.value.currentPage,
                    size = _uiState.value.pageSize
                )
                val gamesPage = response.data
                val newGames = gamesPage?.content ?: emptyList()

                if (newGames.isEmpty() || gamesPage?.isLast == true) {
                    updateUiState(loadingMore = false, hasMoreData = false)
                } else {
                    val currentGames = _uiState.value.resultGame.toMutableList()
                    currentGames.addAll(newGames)
                    updateUiState(
                        resultGame = currentGames,
                        loadingMore = false,
                        currentPage = _uiState.value.currentPage + 1,
                        hasMoreData = !gamesPage?.isLast!!
                    )

                }
            } catch (e: Exception) {
                updateUiState(
                    loadingMore = false,
                    error = "Error loading more games: ${e.message}"
                )
            } finally {
                updateUiState(loadingMore = false)
            }
        }
    }

    fun refreshGames() {
        updateUiState(
            currentPage = 0,
            hasMoreData = true,
            isLoadingMore = false
        )
        findGames()
    }
    fun reset(){
        updateUiState(resultReview = emptySet(), resultGame = emptyList(), search = "", filter = "")
    }

    fun updateFilter(filter: String) {
        updateUiState(filter = filter)
    }

    fun updateSearch(search: String) {
        updateUiState(search = search)
        findGames()
    }

    private fun updateUiState(
        resultGame: List<GameDto> = _uiState.value.resultGame,
        resultReview: Set<ReviewCrowGames> = _uiState.value.resultReview,
        search: String = _uiState.value.search,
        filter: String = _uiState.value.filter,
        tabs: List<String> = _uiState.value.tabs,
        loading: Boolean = _uiState.value.loading,
        loadingMore: Boolean = _uiState.value.loadingMore,
        error: String? = _uiState.value.error,
        currentPage: Int = _uiState.value.currentPage,
        isLoadingMore: Boolean = _uiState.value.isLoadingMore,
        hasMoreData: Boolean = _uiState.value.hasMoreData,
        pageSize: Int = _uiState.value.pageSize,
        isLoaded: Boolean = _uiState.value.isLoaded,
        loadedImages: Int = _uiState.value.loadedImages,
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                resultGame = resultGame,
                resultReview = resultReview,
                search = search,
                filter = filter,
                tabs = tabs,
                loading = loading,
                loadingMore = loadingMore,
                currentPage = currentPage,
                isLoadingMore = isLoadingMore,
                hasMoreData = hasMoreData,
                pageSize = pageSize,
                error = error,
                isLoaded = isLoaded,
                loadedImages = loadedImages
            )
        }
    }

    data class ScreenUiState(
        val search: String = "",
        val filter: String = "Games",
        val tabs: List<String> = listOf("Games", "Reviews"),
        val resultGame: List<GameDto> = emptyList(),
        val resultReview: Set<ReviewCrowGames> = emptySet(),
        val loading: Boolean = true,
        val loadingMore: Boolean = false,
        var currentPage: Int = 0,
        var isLoadingMore: Boolean = false,
        var hasMoreData: Boolean = true,
        val pageSize: Int = 40,
        val isLoaded: Boolean = false,
        val loadedImages: Int = 0,
        val error: String? = null
    )

}