package org.crow.ui.screens

import androidx.compose.runtime.Composable
import org.crow.viewModel.GameDetailScreenViewModel

@Composable
actual fun GameDetailScreen(
    gameId: Long,
    viewModel: GameDetailScreenViewModel,
    onClickArrowBack: () -> Unit,
    onClickCategory: (String) -> Unit,
    onViewAllClick: () -> Unit,
    onWriteReviewClick: (String, String, Long) -> Unit,
    onClickSnackBar: () -> Unit
) {
}