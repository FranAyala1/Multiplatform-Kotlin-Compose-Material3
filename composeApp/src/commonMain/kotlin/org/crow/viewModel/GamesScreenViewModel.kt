package org.crow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import io.ktor.client.HttpClient
import io.ktor.client.network.sockets.ConnectTimeoutException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.crow.data.DataRepository
import org.crow.model.Game.GameDto


class GamesScreenViewModel(
    private val dataRepository: DataRepository = DataRepository(),
) : ViewModel() {

    private val _uiState: MutableStateFlow<GameUiState> = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()


    init {
        loadInitialGames()
    }

    private fun loadInitialGames() {
        viewModelScope.launch {
            try {
                val response = dataRepository.getGames(page = 0, size = _uiState.value.pageSize)
                updateUiState(
                    gameSet = checkNotNull(response.data?.content),
                    loading = false,
                    currentPage = 1,
                    hasMoreData = response.data?.isLast!=true
                )
            } catch (e: ConnectTimeoutException) {
                updateUiState(loading = false, error = "Timeout error")
            } catch (e: Exception) {
                updateUiState(loading = false, error = "Error loading games: ${e.message}")
            }
        }
    }

    fun updateImages(){
        if(_uiState.value.loadedImages%_uiState.value.pageSize<3){
            updateUiState(isLoaded = true)
        }
        updateUiState(loadedImages = _uiState.value.loadedImages+1)

    }

    fun loadMoreGames() {
        if (_uiState.value.isLoadingMore || !_uiState.value.hasMoreData) return
      updateUiState(loadingMore = true)
        viewModelScope.launch {
            try {
                val response = dataRepository.getGames(page = _uiState.value.currentPage, size = _uiState.value.pageSize)
                val gamesPage = response.data
                val newGames = gamesPage?.content ?: emptyList()

                if (newGames.isEmpty() || gamesPage?.isLast == true) {
                    updateUiState(loadingMore = false, hasMoreData = false)
                } else {
                    val currentGames = _uiState.value.gameSet.toMutableList()
                    currentGames.addAll(newGames)
                    updateUiState(
                        gameSet = currentGames,
                        loadingMore = false,
                        currentPage = _uiState.value.currentPage+1,
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
        loadInitialGames()
    }

    private fun updateUiState(
        gameSet: List<GameDto> = _uiState.value.gameSet,
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
                gameSet = gameSet,
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

    data class GameUiState(
        val gameSet: List<GameDto> = mutableListOf(),
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