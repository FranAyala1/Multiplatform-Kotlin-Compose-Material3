package org.crow.ui.screens


import androidx.compose.runtime.Composable
import org.crow.viewModel.GameDetailScreenViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
expect fun GameDetailScreen(gameId: Long,
                            viewModel: GameDetailScreenViewModel = koinViewModel(
                                parameters = { parametersOf(gameId) }),
                            onClickArrowBack: () -> Unit,
                            onClickCategory: (String) -> Unit,
                            onViewAllClick :() -> Unit,
                            onWriteReviewClick:(String,String,Long) -> Unit,
                            onClickSnackBar:() -> Unit
)
