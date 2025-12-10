package org.crow.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.crow.ui.components.FirstReviews
import org.crow.ui.components.GameHeaderSection
import org.crow.ui.components.GameNameDateSection
import org.crow.ui.components.GameSynopsisSection
import org.crow.ui.components.RatingComponent
import org.crow.ui.components.TabExample
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
    GamesDetailScreen(
        viewModel = viewModel,
        onClickArrowBack = onClickArrowBack,
        onClickCategory = onClickCategory,
        onViewAllClick = onViewAllClick,
        onWriteReviewClick = onWriteReviewClick,
        onClickSnackBar = onClickSnackBar
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GamesDetailScreen(
    viewModel: GameDetailScreenViewModel,
    onClickArrowBack: () -> Unit,
    onClickCategory: (String) -> Unit,
    onViewAllClick: () -> Unit,
    onWriteReviewClick: (String, String, Long) -> Unit,
    onClickSnackBar: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(
        lazyListState.firstVisibleItemIndex,
        lazyListState.firstVisibleItemScrollOffset
    ) {
        val showSolidColor = lazyListState.firstVisibleItemIndex > 0 ||
                lazyListState.firstVisibleItemScrollOffset > 350
        viewModel.updateTopBarState(showSolidColor)
    }

    val animatedTopBarColor by animateColorAsState(
        targetValue = uiState.topBarColor,
        animationSpec = tween(durationMillis = 200),
        label = "topBarColorAnimation"
    )

    Scaffold(
        contentWindowInsets = WindowInsets.statusBars,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    modifier = Modifier.padding(bottom = 100.dp),
                    containerColor = Color(0xFF323232),
                    contentColor = Color.White,
                    actionColor = Color(0xFFBB86FC),
                    dismissActionContentColor = Color.White,
                    snackbarData = data
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    AnimatedVisibility(
                        visible = uiState.isTopBarSolid,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 50.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = uiState.gameDetail.name ?: "Game Details",
                                color = MaterialTheme.colorScheme.primary,
                                maxLines = 1
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onClickArrowBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Menú",
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = animatedTopBarColor,
                    actionIconContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary
                ),
            )
        }
    ) { innerPadding ->
        if (uiState.loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(bottom = 120.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    GameHeaderSection(
                        gameUrl = when {
                            !uiState.gameDetail.coversGame?.largeCover.isNullOrEmpty() ->
                                uiState.gameDetail.coversGame?.largeCover?.first()?.url.toString()

                            !uiState.gameDetail.coversGame?.normalCover.isNullOrEmpty() ->
                                uiState.gameDetail.coversGame?.normalCover?.first()?.url.toString()

                            else -> uiState.gameDetail.capsuleImage.toString()
                        },

                        )
                }
                item {
                    GameNameDateSection(
                        gameUrl = when {
                            !uiState.gameDetail.coversGame?.normalCover.isNullOrEmpty() ->
                                uiState.gameDetail.coversGame?.normalCover?.first()?.url.toString()

                            !uiState.gameDetail.coversGame?.largeCover.isNullOrEmpty() ->
                                uiState.gameDetail.coversGame?.largeCover?.first()?.url.toString()

                            else -> uiState.gameDetail.capsuleImage.toString()
                        }, name = uiState.gameDetail.name ?: "",
                        gameDate = uiState.gameDetail.releaseDate?.date ?: "",
                        developer = uiState.gameDetail.developers?.get(0) ?: ""
                    )
                }
                item {
                    GameSynopsisSection(
                        gameDescription = uiState.gameDetail.shortDescription ?: "",
                        expanded = uiState.expandedText,
                        onClickText = { viewModel.updateExpandedText() })
                }
                item {
                    RatingComponent(
                        metracriticRating = uiState.gameDetail.metacritic?.score ?: 0,
                        averageRating = uiState.gameDetail.averageRating ?: 0.toDouble(),
                        totalRatings = uiState.gameDetail.totalRatings ?: 0,
                    )
                }
                item {
                    TabExample(
                        modifier = Modifier,
                        tabsList = uiState.tabList ?: emptyList(),
                        selectedTabIndex = uiState.selectedTabIndex,
                        onClickCategory = { onClickCategory(it) },
                        onClickTab = { viewModel.clickTab(it) },
                        categorySet = uiState.gameDetail.categories ?: emptySet(),
                        languages = uiState.gameDetail.supportedLanguages ?: "",
                        onClickLanguage = {},
                        pcRequirements = uiState.gameDetail.pcRequirements ?: "",
                        covers = uiState.gameDetail.coversGame?.normalCover ?: emptyList(),
                        dlc = uiState.gameDetail.dlc ?: emptyList()
                    )
                }
                item {
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                    )
                }
                item {
                    FirstReviews(
                        uiState.reviews,
                        onWriteReviewClick = {
                            if (uiState.loggedIn) {
                                onWriteReviewClick(
                                    uiState.gameDetail.coversGame?.normalCover?.get(0)?.url ?: "",
                                    uiState.gameDetail.name ?: "",
                                    uiState.gameDetail.id
                                )
                            } else {
                                coroutineScope.launch {
                                    val snackbarResult = snackbarHostState.showSnackbar(
                                        message = "You need to login to write a Review",
                                        actionLabel = "Login",
                                        withDismissAction = true,
                                        duration = SnackbarDuration.Short
                                    )
                                    when (snackbarResult) {
                                        SnackbarResult.ActionPerformed -> onClickSnackBar()
                                        SnackbarResult.Dismissed -> {
                                        }
                                    }
                                }
                            }
                        }, onViewAllClick = onViewAllClick
                    )
                }
            }
        }
    }
}