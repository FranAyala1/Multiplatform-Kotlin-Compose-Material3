package org.crow.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AppBarWithSearch
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExpandedDockedSearchBar
import androidx.compose.material3.ExpandedFullScreenSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch
import org.crow.model.Game.GameDto
import org.crow.model.ReviewCrowGames
import org.crow.ui.topBar.SearchGraphTopBar
import org.crow.viewModel.SearchScreenViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: SearchScreenViewModel = koinViewModel(), onClick: (GameDto) -> Unit) {
    Screen {
        val uiState by viewModel.uiState.collectAsState()
        val lazyGridState = rememberLazyGridState()

        val shouldLoadMore = remember {
            derivedStateOf {
                val layoutInfo = lazyGridState.layoutInfo
                val totalItemsCount = layoutInfo.totalItemsCount
                val lastVisibleItemIndex =
                    layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                lastVisibleItemIndex >= totalItemsCount - 5 &&
                        !uiState.loadingMore &&
                        uiState.resultGame.isNotEmpty()
            }
        }


        LaunchedEffect(shouldLoadMore) {
            snapshotFlow { shouldLoadMore.value }
                .collect { shouldLoad ->
                    if (shouldLoad) {
                        viewModel.loadMoreGames()
                    }
                }
        }
        val textFieldState = rememberTextFieldState()
        val searchBarState = rememberSearchBarState()
        val scope = rememberCoroutineScope()
        val scrollBehavior = SearchBarDefaults.enterAlwaysSearchBarScrollBehavior()
        val appBarWithSearchColors = SearchBarDefaults.appBarWithSearchColors()
        val pagerState = rememberPagerState(pageCount = { uiState.tabs.size })
        val coroutineScope = rememberCoroutineScope()
        val inputField =
            @Composable {
                SearchBarDefaults.InputField(
                    modifier = Modifier,
                    searchBarState = searchBarState,
                    textFieldState = textFieldState,
                    onSearch = { viewModel.updateSearch(it) },
                    placeholder = {
                        if (searchBarState.currentValue == SearchBarValue.Collapsed) {
                            Text(
                                modifier = Modifier.fillMaxWidth().clearAndSetSemantics {},
                                text = "Search",
                                textAlign = TextAlign.Center,
                            )
                        }
                    },
                    leadingIcon = {
                        if (searchBarState.currentValue == SearchBarValue.Expanded) {
                            TooltipBox(
                                positionProvider =
                                    TooltipDefaults.rememberTooltipPositionProvider(
                                        TooltipAnchorPosition.Above
                                    ),
                                tooltip = { PlainTooltip { Text("Back") } },
                                state = rememberTooltipState(),
                            ) {
                                IconButton(
                                    onClick = {
                                        scope.launch {
                                            searchBarState.animateToCollapsed()
                                            viewModel.reset()
                                        }
                                    }
                                ) {
                                    Icon(
                                        Icons.AutoMirrored.Default.ArrowBack,
                                        contentDescription = "Back",
                                    )
                                }
                            }
                        } else {
                            Icon(Icons.Default.Search, contentDescription = null)
                        }
                    }
                )
            }

        Scaffold(
            topBar = {
                AppBarWithSearch(
                    state = searchBarState,
                    inputField = inputField,
                    colors = appBarWithSearchColors,
                )
                ExpandedFullScreenSearchBar(
                    state = searchBarState, inputField = inputField, colors = SearchBarColors(
                        containerColor = MaterialTheme.colorScheme.scrim,
                        dividerColor = MaterialTheme.colorScheme.scrim,
                        inputFieldColors = TextFieldDefaults.colors().copy(
                            disabledContainerColor = Color.Transparent,
                            disabledLeadingIconColor = Color.Transparent
                        )
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(bottom = 100.dp)
                            .fillMaxSize()
                    ) {
                        // 1. Scrollable Tab Row
                        PrimaryScrollableTabRow(
                            selectedTabIndex = pagerState.currentPage,
                            contentColor = MaterialTheme.colorScheme.primary,
                            containerColor = MaterialTheme.colorScheme.scrim,
                            edgePadding = 0.dp
                        ) {
                            uiState.tabs.forEachIndexed { index, tab ->
                                Tab(
                                    selected = pagerState.currentPage == index,
                                    onClick = {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(index)
                                            viewModel.updateFilter(tab)
                                        }
                                    },
                                    text = { Text(text = tab) }
                                )
                            }
                        }

                        // 2. Horizontal Pager for swipeable content
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                        ) { pageIndex ->
                            Surface(color = MaterialTheme.colorScheme.surface) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column {
                                        if (uiState.resultGame.isEmpty()) {
                                            Box(
                                                modifier = Modifier.height(50.dp),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = "0 Games found",
                                                    style = MaterialTheme.typography.titleMedium
                                                )
                                            }
                                        }
                                        if (uiState.loading) {
                                            Box(
                                                modifier = Modifier.height(100.dp)
                                                    .padding(horizontal = 35.dp),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                CircularProgressIndicator()
                                            }
                                        }
                                    }
                                    if (uiState.filter == "Games" || pageIndex == 0) {
                                        LazyVerticalGrid(
                                            columns = GridCells.Adaptive(120.dp),
                                            contentPadding = PaddingValues(0.dp),
                                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                                            verticalArrangement = Arrangement.spacedBy(8.dp),
                                            modifier = Modifier.padding(
                                                top = 10.dp
                                            ),
                                            state = lazyGridState
                                        ) {
                                            items(
                                                uiState.resultGame,
                                                key = { it.steamAppid }) { game ->
                                                Item(
                                                    game,
                                                    onClick = { onClick(game) },
                                                    onSuccesImage = { viewModel.updateImages() })
                                            }

                                            if (uiState.loadingMore) {
                                                item {
                                                    Box(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(16.dp),
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        Column(
                                                            horizontalAlignment = Alignment.CenterHorizontally
                                                        ) {
                                                            CircularProgressIndicator(
                                                                modifier = Modifier.size(40.dp),
                                                                strokeWidth = 3.dp
                                                            )
                                                            Spacer(modifier = Modifier.height(8.dp))
                                                            Text(
                                                                "Cargando más juegos...",
                                                                style = MaterialTheme.typography.bodySmall,
                                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        LazyVerticalGrid(
                                            columns = GridCells.Adaptive(300.dp),
                                            contentPadding = PaddingValues(0.dp),
                                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                                            verticalArrangement = Arrangement.spacedBy(8.dp),
                                            modifier = Modifier.padding(
                                                top = 10.dp
                                            ),
                                            state = lazyGridState
                                        ) {
                                            items(
                                                uiState.resultReview.size,
                                            ) { review ->
                                                var expanded by remember { mutableStateOf(false) }
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(8.dp)
                                                        .background(MaterialTheme.colorScheme.surfaceVariant)
                                                ) {
                                                    Row(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(16.dp),
                                                        horizontalArrangement = Arrangement.SpaceBetween
                                                    ) {
                                                        Column(
                                                            modifier = Modifier.weight(1f),
                                                            verticalArrangement = Arrangement.spacedBy(
                                                                8.dp
                                                            )
                                                        ) {
                                                            // Nombre de usuario
                                                            Text(
                                                                text = uiState.resultReview.elementAt(index = review).username,
                                                                style = MaterialTheme.typography.titleMedium.copy(
                                                                    fontWeight = FontWeight.Bold
                                                                ),
                                                                color = MaterialTheme.colorScheme.primary
                                                            )

                                                            Column() {
                                                                AnimatedContent(
                                                                    targetState = expanded,
                                                                    label = "textAnimation"
                                                                ) { isExpanded ->
                                                                    Text(
                                                                        text = uiState.resultReview.elementAt(index = review).reviewCrow.contenido
                                                                            ?: "",
                                                                        style = MaterialTheme.typography.bodyMedium,
                                                                        maxLines = if (isExpanded) Int.MAX_VALUE else 5,
                                                                        lineHeight = 20.sp
                                                                    )
                                                                }
                                                                if (!expanded) {
                                                                    Text(
                                                                        text = "Ver más",
                                                                        color = MaterialTheme.colorScheme.primary,
                                                                        style = MaterialTheme.typography.labelSmall
                                                                    )
                                                                }
                                                            }
                                                            // Comentario

                                                            // Rating
                                                            Row(
                                                                verticalAlignment = Alignment.CenterVertically,
                                                                horizontalArrangement = Arrangement.spacedBy(
                                                                    4.dp
                                                                )
                                                            ) {
                                                                // Estrellas de rating
                                                                repeat(
                                                                    uiState.resultReview.elementAt(index = review).ratingCrow.rating ?: 0
                                                                ) {
                                                                    Icon(
                                                                        imageVector = Icons.Filled.Star,
                                                                        contentDescription = null,
                                                                        tint = Color(0xFFFFD700),
                                                                        modifier = Modifier.size(18.dp)
                                                                    )
                                                                }
                                                                Text(
                                                                    text = uiState.resultReview.elementAt(index = review).ratingCrow.rating.toString(),
                                                                    style = MaterialTheme.typography.bodyMedium,
                                                                    fontWeight = FontWeight.Medium,
                                                                    modifier = Modifier.padding(
                                                                        start = 4.dp
                                                                    )
                                                                )
                                                            }
                                                        }

                                                        AsyncImage(
                                                            model = uiState.resultReview.elementAt(index = review).url,
                                                            contentDescription = "Game cover",
                                                            contentScale = ContentScale.Crop,
                                                            modifier = Modifier
                                                                .padding(16.dp)
                                                                .height(150.dp)
                                                                .aspectRatio(2 / 3f)
                                                                .clip(MaterialTheme.shapes.small)
                                                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            },
        )
        {

        }
    }
}

@Composable
private fun Item(
    gameDto: GameDto,
    onClick: () -> Unit,
    onSuccesImage: () -> Unit
) {
    Column(modifier = Modifier.clickable(onClick = onClick)) {
        AsyncImage(
            model = if (gameDto.coversGame?.normalCover != null && gameDto.coversGame?.normalCover?.isEmpty() != true) gameDto.coversGame?.normalCover?.get(
                0
            )?.url else gameDto.capsuleImage,
            contentDescription = gameDto.shortDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(2 / 3f)
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.primaryContainer),
            onSuccess = { onSuccesImage }
        )
        Text(
            text = gameDto.name.toString(),
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            modifier = Modifier.padding(8.dp)
        )
    }
}