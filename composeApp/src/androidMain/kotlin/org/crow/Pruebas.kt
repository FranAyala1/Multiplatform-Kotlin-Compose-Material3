package org.crow

import android.annotation.SuppressLint
import android.app.appsearch.SearchResults
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.material3.Icon
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.crow.model.Game.CategoryDto
import org.crow.model.Game.GameDto
import org.crow.ui.components.extractLanguagesPrecise
import org.crow.ui.theme.CrowTheme
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle

import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.text.input.ImeAction
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.semantics.clearAndSetSemantics
import kotlinx.coroutines.launch

//
//@Preview
//@Composable
//fun NewsCard() {
//    CrowTheme {
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(MaterialTheme.colorScheme.onSurfaceVariant)
//        ) {
//            item { FilmHeaderSection() }
//            item { FilmSynopsisSection() }
//            item { RatingComponent() }
//            item { TabExample() }
//            item { FilmDetailsTable() }
//            item { FilmDetailRow() }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun FilmHeaderSection() {
//    // Use a Box to overlay the poster and the title/year
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(200.dp) // Adjust height as needed
//    ) {
//        // Film Poster as background
//        AsyncImage(
//            model = "",
//            contentDescription = "Film Poster for",
//            modifier = Modifier
//                .fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
//        // Gradient or scrim for text readability
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(
//                    brush = Brush.verticalGradient(
//                        colors = listOf(
//                            Color.Black.copy(alpha = 0.1f),
//                            Color.Transparent,
//                            Color.Transparent,
//                            Color.Transparent,
//                            Color.Black.copy(alpha = 0.1f)
//                        ),
//                        startY = 0f,
//                        endY = Float.POSITIVE_INFINITY
//                    )
//                )
//        )
//        // Film Title and Year
//        Column(
//            modifier = Modifier
//                .align(Alignment.BottomStart)
//                .padding(16.dp)
//        ) {
//            Text(
//                text = "game.name" ?: "",
//                style = MaterialTheme.typography.headlineLarge,
//                color = Color.Black
//            )
//            Text(
//                text = "game.releaseDate?.date" ?: "",
//                style = MaterialTheme.typography.titleMedium,
//                color = Color.White.copy(alpha = 0.9f)
//            )
//        }
//    }
//}
//
//@Preview
//@Composable
//fun FilmSynopsisSection() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(200.dp)
//            .padding(4.dp)
//    ) {
//        AsyncImage(
//            model = "gameDto.coversGame?.get(0)?.url",
//            contentDescription = "gameDto.sihortDescripton",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .aspectRatio(2 / 3f)
//                .clip(MaterialTheme.shapes.small)
//                .background(onPrimaryDark)
//        )
//        Column(
//            modifier = Modifier
//                .fillMaxHeight()
//                .padding(start = 70.dp)
//        ) {
//            Text(
//                text = "About the game",
//                style = MaterialTheme.typography.titleLarge,
//                modifier = Modifier.padding(bottom = 8.dp),
//                color = onPrimaryDark
//            )
//            Text(
//                text = "synopsis",
//                style = MaterialTheme.typography.bodyLarge,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(4.dp),
//                color = onPrimaryDark
//            )
//        }
//    }
//}
//
//@Preview
//@Composable
//fun FilmDetailsTable() {
//    Column(modifier = Modifier.padding(16.dp)) {
//        FilmDetailRow()
//        FilmDetailRow()
//        FilmDetailRow()
//    }
//}
//
//@Preview
//@Composable
//fun FilmDetailRow() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 4.dp),
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Text(
//            text = "title",
//            style = MaterialTheme.typography.bodyMedium,
//            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
//            modifier = Modifier.weight(1f)
//        )
//        Text(
//            text = "value",
//            style = MaterialTheme.typography.bodyMedium,
//            textAlign = TextAlign.End,
//            modifier = Modifier.weight(1.5f)
//        )
//    }
//}
//
//@Preview
//@Composable
//fun TabExample() {
//    // 1. Manage the selected tab state
//    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
//    val tabTitles = listOf("Category", "Requirements", "Covers", "DLC", "Details")
//
//    // 2. Create the Tab Row and Tabs
//    PrimaryScrollableTabRow(
//        selectedTabIndex = selectedTabIndex,
//        modifier = Modifier.padding(top = 10.dp),
//        containerColor = onPrimaryDark,
//        contentColor = primaryDark
//    ) {
//        tabTitles.forEachIndexed { index, title ->
//            Tab(
//                selected = selectedTabIndex == index,
//                onClick = { selectedTabIndex = index }, // Update state on click
//                text = { Text(text = title) }
//            )
//        }
//    }
//
//    // 3. Display content based on selection
//    // You might use a HorizontalPager here for swipeable content
//    when (selectedTabIndex) {
//        0 -> FancyTab("a", {}, true)
//        1 -> FancyTab("", {}, false)
//        2 -> FancyTab("c", {}, true)
//    }
//}
//
//@Composable
//fun FancyTab(title: String, onClick: () -> Unit, selected: Boolean) {
//    Tab(selected, onClick ) {
//        Column(
//            Modifier
//                .padding(10.dp)
//                .height(50.dp)
//                .fillMaxWidth(),
//            verticalArrangement = Arrangement.SpaceBetween,
//        ) {
//            // Custom indicator
//            Text(text = title)
//        }
//    }
//}
//
//@Preview
//@Composable
//fun RatingComponent() {
//    val distributionData = remember {
//        listOf(
//            5 to 452f, // 5 stars, 452 votes
//            4 to 280f, // 4 stars, 280 votes
//            3 to 150f, // 3 stars, 150 votes
//            2 to 80f,  // 2 stars, 80 votes
//            1 to 38f   // 1 star, 38 votes
//        )
//    }
//    Row(
//        modifier = Modifier
//            .height(70.dp)
//            .fillMaxWidth().padding(10.dp)
//    ) {
//        LetterboxdRatingSection()
//        SimpleStarRating(rating = 3.65f)
//    }
//}
//
//@Composable
//fun SimpleStarRating(
//    modifier: Modifier = Modifier,
//    rating: Float,
//) {
//    Column(modifier = modifier.padding(start = 70.dp)) {
//        Row {
//            Icon(
//                imageVector = Icons.Filled.Star,
//                contentDescription = null,
//                tint = primaryLight,
//            )
//            Text(text = rating.toString(), modifier = Modifier.padding(4.dp))
//        }
//        Text(text = "IMDB Rating", modifier = Modifier.padding(horizontal = 4.dp))
//    }
//}
//
//@Preview
//@Composable
//fun LetterboxdRatingSection(
//    modifier: Modifier = Modifier,
//    averageRating: Float = 3.82f,
//    totalRatings: Int = 127543,
//) {
//    Column(modifier = modifier) {
//        // Average rating row
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            Text(
//                text = averageRating.toString(),
//                style = MaterialTheme.typography.displaySmall,
//                fontWeight = FontWeight.Bold,
//                color = primaryLight
//            )
//
//            Column {
//                // Interactive star rating component
//                RatingStars(
//                    rating = averageRating,
//                    maxRating = 5,
//                    onRatingChanged = { },
//                    isIndicator = true
//                )
//
//                Text(
//                    text = "$totalRatings ratings",
//                    style = MaterialTheme.typography.bodySmall,
//                    color = MaterialTheme.colorScheme.onSurfaceVariant
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun RatingStars(
//    rating: Float,
//    maxRating: Int = 5,
//    onRatingChanged: (Int) -> Unit,
//    isIndicator: Boolean = false
//) {
//    Row {
//        repeat(maxRating) { index ->
//            val starNumber = index + 1
//            val isSelected = starNumber <= rating
//
//            // Choose the icon based on selection
//            val imageVector = if (isSelected) Icons.Filled.Star else Icons.Outlined.Star
//            val tintColor = if (isSelected) Color(0xFF572364) else Color.LightGray
//
//            Icon(
//                imageVector = imageVector,
//                contentDescription = null,
//                tint = tintColor,
//                modifier = Modifier
//                    .clickable { onRatingChanged(starNumber) }
//            )
//        }
////            if (i <= rating.toInt()) {
////                // Full stars
////                Icon(
////                    imageVector = Icons.Filled.Star,
////                    contentDescription = null,
////                    tint = Color(0xFF572364),
////                    modifier = Modifier.size(20.dp)
////                )
////            } else if (i == rating.toInt() + 1 && rating % 1 != 0f) {
////                // Partial star - you can implement this with custom drawing
////                Icon(
////                    imageVector = Icons.Filled.Star,
////                    contentDescription = null,
////                    tint = Color(0xFF572364),
////                    modifier = Modifier.size(20.dp)
////                )
////            } else {
////                // Empty stars
////                Icon(
////                    imageVector = Icons.Outlined.Star,
////                    contentDescription = null,
////                    tint = Color(0xFFD3D3D3),
////                    modifier = Modifier.size(20.dp)
////                )
////            }
//    }
//}

//////////////////////////////////////////////////////////////////////////

@Preview
@Composable
private fun NewsCard() {
    CrowTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            item { GameHeaderSection() }
            item { GameNameDateSection() }
            item { GameSynopsisSection() }
            item { RatingComponent() }
            item { TabExample() }
            item { CrowComments() }
            item { FilmDetailRow() }
        }
    }
}

@Preview
@Composable
fun GameHeaderSection(
    modifier: Modifier = Modifier,
    gameUrl: String = "",
    name: String = "",
    gameDate: String = ""
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        AsyncImage(
            model = gameUrl,
            contentDescription = "Game Poster",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.4f),
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.6f),
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                            MaterialTheme.colorScheme.surface
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview
@Composable
fun GameNameDateSection(
    modifier: Modifier = Modifier,
    gameUrl: String = "",
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),

        ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = "name largo",
                style = MaterialTheme.typography.headlineLargeEmphasized,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "19 Nov 1999" ?: "",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
            )
            Text(
                text = "Develop by Valve" ?: "",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
            )
        }
        AsyncImage(
            model = gameUrl,
            contentDescription = "Game cover",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .weight(0.6f)
                .padding(16.dp)
                .height(150.dp)
                .aspectRatio(2 / 3f)
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        )
    }
}

@Composable
fun GameSynopsisSection(
    modifier: Modifier = Modifier,
    gameUrl: String = "",
    gameDescription: String = "\"Play the world's number 1 online action game. Engage in an incredibly realistic brand of terrorist warfare in this wildly popular team-based game. Ally with teammates to complete strategic missions. Take out enemy sites. Rescue hostages. Your role affects your team's success. Your team's success affects your role."
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        var expanded by remember { mutableStateOf(false) }

        Column(modifier = Modifier.padding(16.dp)) {
            AnimatedContent(
                targetState = expanded,
                label = "textAnimation"
            ) { isExpanded ->
                Text(
                    text = gameDescription,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 4,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
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
    }
//        Column(
//            modifier = Modifier.padding(start = 50.dp)
//
//        ) {
//            Text(
//                text = "About the game",
//                style = MaterialTheme.typography.titleLarge,
//                color = MaterialTheme.colorScheme.onSurface,
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//            Text(
//                text = gameDescription,
//                maxLines = 5,
//                style = MaterialTheme.typography.bodyLarge,
//                color = MaterialTheme.colorScheme.onSurfaceVariant,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(4.dp)
//            )
//        }
}

//@Preview
//@Composable
//private fun CrowComments() {
//    Column(
//        modifier = Modifier
//            .padding(16.dp)
//            .background(MaterialTheme.colorScheme.surface)
//    ) {
//        FilmDetailRow()
//        FilmDetailRow()
//        FilmDetailRow()
//    }
//}

@Preview
@Composable
private fun FilmDetailRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Título",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "Valor",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1.5f)
        )
    }
}

@Composable
private fun TabExample() {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val tabTitles = listOf("Category", "Details", "Covers", "Requirements", "DLC")
    Column() {
        PrimaryScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.padding(top = 10.dp),
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            edgePadding = 5.dp
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTabIndex == index) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    }
                )
            }
        }
        when (selectedTabIndex) {
            0 -> CategoryTab("Category Content")
            1 -> DetailsTab("\"English<strong>*</strong>, French<strong>*</strong>, German<strong>*</strong>, Italian<strong>*</strong>, Spanish - Spain<strong>*</strong>, Simplified Chinese<strong>*</strong>, Traditional Chinese<strong>*</strong>, Korean<strong>*</strong><br><strong>*</strong>languages with full audio support\"")
//            2 -> FancyTab("Covers Content")
        }
    }
}

@Composable
fun CategoryTab(
    title: String = "Holaaaaaaaa",
//    onClick: () -> Unit,
    categoryList: List<CategoryDto> = List<CategoryDto>(10, init = { CategoryDto(1, "a") }),
    selected: Boolean = true
) {
    FlowRow(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        categoryList.forEach { category ->
            OutlinedCard(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable { },
                colors = CardDefaults.outlinedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                ) {
                    Text(
                        text = category.description ?: "",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
fun MinimalLanguageItem(
    language: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.small
            )
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = language,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}
@Composable
fun CoversScreen(
//    gameDto: GameDto,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(bottom = 90.dp, top = 10.dp),
    ) {
        items(10) { game ->
            Item()
        }
    }
}

@Composable
fun Item(
//    gameDto: GameDto,
//    onClick: () -> Unit,
//    onSuccesImage: () -> Unit
) {
    Column(modifier = Modifier.clickable(onClick = {  })) {
        AsyncImage(
            model = "",
            contentDescription = "gameDto.shortDescription",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(2 / 3f)
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.primaryContainer),
//            onSuccess = {onSuccesImage}
        )
    }
}
@Composable
fun DetailsTab(
    languages: String = "English<strong>*</strong>, French<strong>*</strong>, German<strong>*</strong>, Italian<strong>*</strong>, Spanish - Spain<strong>*</strong>, Simplified Chinese<strong>*</strong>, Traditional Chinese<strong>*</strong>, Korean<strong>*</strong><br><strong>*</strong>languages with full audio support",
    onLanguageClick: (String) -> Unit = {}
) {
    val extractedLanguages = extractLanguagesPrecise(languages)

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Language,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Supported Language",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        LazyColumn {
            items(extractedLanguages) { language ->
                MinimalLanguageItem(
                    language = language,
                    onClick = { onLanguageClick(language) },
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}
//@Preview
//@Composable
//fun RequirementsTab(
//    requirements: String = "MÍNIMO: <strong>Minimum:</strong><br><ul class="+"\"bb_ul\""+"><li><strong>OS *:</strong> Windows® 7 32/64-bit / Vista 32/64 / XP<br></li><li><strong>Processor:</strong> Pentium 4 3.0GHz<br></li><li><strong>Memory:</strong> 2 GB RAM<br></li><li><strong>Graphics:</strong> Video card with 128 MB, Shader model 2.0. ATI X800, NVidia 6600 or better<br></li><li><strong>DirectX:</strong> Version 9.0c<br></li><li><strong>Storage:</strong> 13 GB available space<br></li><li><strong>Sound Card:</strong> DirectX 9.0c compatible sound card</li></ul>"+
//"RECOMENDADO: <strong>Recommended:</strong><br><ul class="+"\"bb_ul\""+"><li><strong>OS *:</strong> Windows® 7 32/64-bit / Vista 32/64 / XP<br></li><li><strong>Processor:</strong> Intel core 2 duo 2.4GHz<br></li><li><strong>Memory:</strong> 2 GB RAM<br></li><li><strong>Graphics:</strong> Video Card Shader model 3.0. NVidia 7600, ATI X1600 or better<br></li><li><strong>DirectX:</strong> Version 9.0c<br></li><li><strong>Storage:</strong> 13 GB available space<br></li><li><strong>Sound Card:</strong> DirectX 9.0c compatible sound card</li></ul>",
//           onLanguageClick: (String) -> Unit = {}
//) {
//    val extractedLanguages = parsePCRequirements(requirements)
//
//    Column(
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxWidth()
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.padding(bottom = 16.dp)
//        ) {
//            Icon(
//                imageVector = Icons.Default.Computer,
//                contentDescription = null,
//                tint = MaterialTheme.colorScheme.primary,
//                modifier = Modifier.size(24.dp)
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(
//                text = "PC Requirements",
//                style = MaterialTheme.typography.bodyLarge,
//                color = MaterialTheme.colorScheme.primary,
//            )
//        }
//        Column {
//            Text(
//                text = extractedLanguages,
//                overflow = TextOverflow.Ellipsis,
//                color = MaterialTheme.colorScheme.onSurface
//            )
//        }
//    }
//}
//
//fun parsePCRequirements(pcText: String): String {
//    val builder = StringBuilder()
//
//    // Extraer sección MÍNIMO
//    val minimoSection = pcText.substringBefore("RECOMENDADO:")
//    val recomendadoSection = pcText.substringAfter("RECOMENDADO:")
//
//    // Parsear MÍNIMO
//    val minimoItems = extractListItems(minimoSection.substringAfter("<ul class=\"bb_ul\">"))
//    builder.append("REQUISITOS MÍNIMOS PARA PC (1080p/60Hz):\n")
//    minimoItems.forEach { item ->
//        builder.append("• ${cleanText(item)}\n")
//    }
//
//    // Parsear RECOMENDADO
//    val recomendadoItems = extractListItems(recomendadoSection.substringAfter("<ul class=\"bb_ul\">"))
//    builder.append("\nREQUISITOS RECOMENDADOS PARA PC (4K/120Hz):\n")
//    recomendadoItems.forEach { item ->
//        builder.append("• ${cleanText(item)}\n")
//    }
//
//    // Agregar nota final si existe
//    if (recomendadoSection.contains("*Compatible display required")) {
//        builder.append("• Nota: Se requiere pantalla compatible para 4K/120Hz\n")
//    }
//
//    return builder.toString()
//}
//
//fun parseMacRequirements(macText: String): String {
//    val builder = StringBuilder()
//
//    // Extraer items después de la lista
//    val itemsText = macText.substringAfter("<ul class=\"bb_ul\">")
//    val items = extractListItems(itemsText)
//
//    builder.append("REQUISITOS MÍNIMOS PARA MAC:\n")
//    items.forEach { item ->
//        // Limpiar etiquetas <strong> y formatos
//        val cleanedItem = item
//            .replace("<strong>", "")
//            .replace("</strong>", "")
//            .replace("<br>", "")
//        builder.append("• ${cleanText(cleanedItem)}\n")
//    }
//
//    return builder.toString()
//}
//
//fun parseLinuxRequirements(linuxText: String): String {
//    val builder = StringBuilder()
//
//    // Extraer items después de la lista
//    val itemsText = linuxText.substringAfter("<ul class=\"bb_ul\">")
//    val items = extractListItems(itemsText)
//
//    builder.append("REQUISITOS MÍNIMOS PARA LINUX:\n")
//    items.forEach { item ->
//        // Limpiar etiquetas <strong> y formatos
//        val cleanedItem = item
//            .replace("<strong>", "")
//            .replace("</strong>", "")
//            .replace("<br>", "")
//        builder.append("• ${cleanText(cleanedItem)}\n")
//    }
//
//    return builder.toString()
//}
//
//// Funciones auxiliares
//private fun extractListItems(html: String): List<String> {
//    val pattern = Regex("<li>(.*?)</li>", RegexOption.DOT_MATCHES_ALL)
//    return pattern.findAll(html).map { it.groupValues[1] }.toList()
//}
//
//private fun cleanText(text: String): String {
//    return text
//        .replace(Regex("<[^>]+>"), "")
//        .replace("\\s+".toRegex(), " ")
//        .trim()
//        .replace(Regex("\\s*<br>\\s*"), "\n")
//}

@Preview
@Composable
private fun RatingComponent() {
    Row(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        LetterboxdRatingSection()
        SimpleStarRating(rating = 3.65f)
    }
}

@Composable
fun SimpleStarRating(
    modifier: Modifier = Modifier,
    rating: Float,
) {
    Column(modifier = modifier.padding(start = 70.dp)) {
        Row {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = rating.toString(),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(4.dp)
            )
        }
        Text(
            text = "IMDB Rating",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}

@Preview
@Composable
private fun LetterboxdRatingSection(
    modifier: Modifier = Modifier,
    averageRating: Float = 3.82f,
    totalRatings: Int = 127543,
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = averageRating.toString(),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Column {
                RatingStars(
                    rating = averageRating,
                    maxRating = 5,
                    onRatingChanged = { },
                    isIndicator = true
                )
                Text(
                    text = "$totalRatings ratings",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun RatingStars(
    rating: Float,
    maxRating: Int = 5,
    onRatingChanged: (Int) -> Unit,
    isIndicator: Boolean = false
) {
    Row {
        repeat(maxRating) { index ->
            val starNumber = index + 1
            val isSelected = starNumber <= rating

            val imageVector = if (isSelected) Icons.Filled.Star else Icons.Outlined.Star
            val tintColor = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.outline
            }

            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = tintColor,
                modifier = Modifier
                    .size(20.dp)
                    .clickable(enabled = !isIndicator) { onRatingChanged(starNumber) }
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun UserScreen() {
    // Lista de elementos de ejemplo
    val items = List(50) { "Item ${it + 1}" }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        contentWindowInsets = WindowInsets.statusBars,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                                MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                                MaterialTheme.colorScheme.background
                            ),
                            startY = 0f,
                            endY = 800f
                        )
                    )
            )

            // Contenido scrolleable
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 0.dp)
            ) {
                // Header que será visible detrás de la TopAppBar
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Header Scrolleable",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(30.dp)
                        )

                        Button(
                            onClick = {},
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(30.dp)
                        ){
                            Text(text = "Log Out")
                        }
                    }
                }

                items(items.size) { item ->
                    ComentUserScreen(item)
                }
            }
        }
    }
}


@Preview
@Composable
private fun ComentUserScreen(item: Int=1) {
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
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Nombre de usuario
                Text(
                    text = "María García",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )

                var expanded by remember { mutableStateOf(false) }

                Column() {
                    AnimatedContent(
                        targetState = expanded,
                        label = "textAnimation"
                    ) { isExpanded ->
                        Text(
                            text = "Una película increíble con una fotografía espectacular. La actuación del protagonista es simplemente magistral. Definitivamente una de las mejores del año.",
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
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Estrellas de rating
                    repeat(5) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Text(
                        text = "4.5",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }

            AsyncImage(
                model = "gameUrl",
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


@Composable
private fun ComentSectionGame(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Preview
@Composable
private fun ComentsComponent(){
    val items= listOf("a","c","e")
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Column {
            Column {
                items.forEach{
                    ComentGameScreen()
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
            )
            Row(modifier = Modifier
                .padding(10.dp)
                .height(80.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                Card(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()){
                    Box(modifier=Modifier.fillMaxSize()){
                        Text(
                            text = "View All",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
                Card(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()){
                    Box(modifier=Modifier.fillMaxSize()){
                        Text(
                            text = "Write Review",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ComentGameScreen() {
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
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row {
                    Text(
                        text = "María García",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1f)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        // Estrellas de rating
                        repeat(5) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = null,
                                tint = Color(0xFFFFD700),
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Text(
                            text = "4.5",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }


                var expanded by remember { mutableStateOf(false) }

                Column() {
                    AnimatedContent(
                        targetState = expanded,
                        label = "textAnimation"
                    ) { isExpanded ->
                        Text(
                            text = "Una película increíble con una fotografía espectacular. La actuación del protagonista es simplemente magistral. Definitivamente una de las mejores del año.",
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
            }
        }
    }
}

@Composable
fun AuthScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Iniciar Sesión", "Registrarse")

    // Estados para login
    var loginEmail by remember { mutableStateOf("") }
    var loginPassword by remember { mutableStateOf("") }

    // Estados para registro
    var registerName by remember { mutableStateOf("") }
    var registerEmail by remember { mutableStateOf("") }
    var registerPassword by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Bienvenido",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(40.dp))

                PrimaryTabRow(selectedTabIndex = selectedTab) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title) },
                            selected = selectedTab == index,
                            onClick = { selectedTab = index }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                when (selectedTab) {
                    0 -> LoginTab(
                        email = loginEmail,
                        onEmailChange = { loginEmail = it },
                        password = loginPassword,
                        onPasswordChange = { loginPassword = it },
                        onLoginClick = {
                            // Lógica de login
                        }
                    )

                    1 -> RegisterTab(
                        name = registerName,
                        onNameChange = { registerName = it },
                        email = registerEmail,
                        onEmailChange = { registerEmail = it },
                        password = registerPassword,
                        onPasswordChange = { registerPassword = it },
                        onRegisterClick = {
                            // Lógica de registro
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTab(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(

        ) {
            if (false) {
                Text(
                    text = "error.response",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                label = { Text("Correo electrónico") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )
        }


        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Contraseña") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Iniciar Sesión")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterTab(
    name: String,
    onNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Nombre completo") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Correo electrónico") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Contraseña") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onRegisterClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Registrarse")
        }
    }
}

@Composable
private fun CrowComments() {
    var commentText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                TextField(
                    value = commentText,
                    onValueChange = { commentText = it },
                    placeholder = { Text("Escribe tu comentario...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        disabledContainerColor = MaterialTheme.colorScheme.surface,
                    )
                )

                Button(
                    onClick = {
                        // Aquí manejarías el envío del comentario
                        commentText = ""
                    },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 8.dp)
                ) {
                    Text("Comentar")
                }
            }
        }

        // Lista de comentarios
        LazyColumn {
            items(comments) { comment ->
                ComentUserScreen()
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                )
            }
        }
    }
}
// Data class para representar un comentario
data class Comment(
    val id: Int,
    val userName: String,
    val content: String,
    val date: String,
    val likes: Int = 0
)

// Datos de ejemplo
private val comments = listOf(
    Comment(
        id = 1,
        userName = "María García",
        content = "¡Excelente película! La trama me mantuvo en suspenso hasta el final.",
        date = "Hace 2 horas",
        likes = 12
    ),
    Comment(
        id = 2,
        userName = "Carlos López",
        content = "Las actuaciones fueron increíbles, especialmente la del protagonista.",
        date = "Hace 1 día",
        likes = 8
    ),
    Comment(
        id = 3,
        userName = "Ana Martínez",
        content = "La fotografía y la banda sonora crean una atmósfera única.",
        date = "Hace 3 días",
        likes = 15
    )
)


@Preview
@Composable
fun WriteReviewScreen(
    movieTitle: String = "Interstellar",
    moviePosterUrl: String = "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
    onReviewSubmitted: (rating: Float, review: String) -> Unit = { _, _ -> }
) {
    var rating by remember { mutableFloatStateOf(0f) }
    var reviewText by remember { mutableStateOf("") }
    var characterCount by remember { mutableIntStateOf(0) }
    val maxCharacters = 500
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Header con título
        Text(
            text = "Escribir Reseña",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Tarjeta de información de la película
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Portada de la película
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(moviePosterUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Portada de $movieTitle",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp, 60.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Título de la película
                Text(
                    text = movieTitle,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Sección de puntuación
        Text(
            text = "Tu puntuación",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Componente de estrellas interactivas
        InteractiveStarRating(
            rating = rating,
            onRatingChanged = { newRating ->
                rating = newRating
            },
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Valor numérico de la puntuación
        if (rating > 0) {
            Text(
                text = String.format("%.1f/5.0", rating),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }

        // Sección de reseña
        Text(
            text = "Tu reseña",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Campo de texto para la reseña
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(MaterialTheme.colorScheme.surface)
                .padding(4.dp)
        ) {
            TextField(
                value = reviewText,
                onValueChange = { text ->
                    if (text.length <= maxCharacters) {
                        reviewText = text
                        characterCount = text.length
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                textStyle = MaterialTheme.typography.bodyMedium,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = "Comparte tu opinión sobre la película...",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                maxLines = 8
            )
        }

        // Contador de caracteres
        Text(
            text = "$characterCount/$maxCharacters",
            style = MaterialTheme.typography.bodySmall,
            color = if (characterCount == maxCharacters) MaterialTheme.colorScheme.error
            else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            textAlign = TextAlign.End
        )

        // Consejos para la reseña
        FlowRow(
            modifier = Modifier.padding(top = 16.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ReviewTipChip("Evita spoilers")
            ReviewTipChip("Sé específico")
            ReviewTipChip("Compara con otras películas")
            ReviewTipChip("Habla sobre actuaciones")
            ReviewTipChip("Menciona la fotografía")
        }

        // Botón de enviar
        Button(
            onClick = {
                focusManager.clearFocus()
                if (rating > 0 && reviewText.isNotBlank()) {
                    onReviewSubmitted(rating, reviewText)
                } else {
                    // Mostrar mensaje de error
                    Toast.makeText(
                        context,
                        if (rating == 0f) "Por favor, selecciona una puntuación"
                        else "Por favor, escribe una reseña",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = rating > 0 && reviewText.isNotBlank(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Publicar reseña",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }

        // Botón de cancelar
        TextButton(
            onClick = {
                focusManager.clearFocus()
                // Navegar atrás o cerrar
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Cancelar")
        }
    }
}

@Composable
fun InteractiveStarRating(
    rating: Float,
    onRatingChanged: (Float) -> Unit,
    modifier: Modifier = Modifier,
    maxStars: Int = 5
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(maxStars) { index ->
            val starValue = index + 1
            val filled = rating >= starValue
            val halfFilled = rating > index.toFloat() && rating < starValue.toFloat()

            Icon(
                imageVector = if (filled) Icons.Filled.Star
                else if (halfFilled) Icons.AutoMirrored.Filled.StarHalf
                else Icons.Filled.StarBorder,
                contentDescription = "Puntuación $starValue de $maxStars",
                tint = if (filled || halfFilled) Color(0xFFFFD700)
                else MaterialTheme.colorScheme.outline,
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        onRatingChanged(starValue.toFloat())
                    }
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = { offset ->
                                // Para selección más precisa (opcional)
                                val xPercent = offset.x / size.width
                                val calculatedRating = if (xPercent < 0.5) {
                                    index + 0.5f
                                } else {
                                    starValue.toFloat()
                                }
                                onRatingChanged(calculatedRating)
                            }
                        )
                    }
            )
        }
    }
}

@Composable
fun ReviewTipChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
enum class Tab(val title: String) {
    FILMS("Films"),
    LISTS("Lists"),
    REVIEWS("Reviews"),
    STATS("Stats")
}
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarTabs() {
    // State for the selected tab and pager
    val pagerState = rememberPagerState(pageCount = { Tab.entries.size })
    val coroutineScope = rememberCoroutineScope()
    val textFieldState = rememberTextFieldState()
    val searchBarState = rememberSearchBarState()
    val scope = rememberCoroutineScope()
    val scrollBehavior = SearchBarDefaults.enterAlwaysSearchBarScrollBehavior()
    val inputField =
        @Composable {
            SearchBarDefaults.InputField(
                modifier = Modifier,
                searchBarState = searchBarState,
                textFieldState = textFieldState,
                onSearch = { scope.launch { searchBarState.animateToCollapsed() } },
                placeholder = { Text("Search...") },
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
                                onClick = { scope.launch { searchBarState.animateToCollapsed() } }
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
                },
                trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
            )
        }

    // Scaffold provides the TopAppBar and content area
    Scaffold(
        topBar = {
            // TopAppBar (Small type as in Letterboxd)
            AppBarWithSearch(
                scrollBehavior = scrollBehavior,
                state = searchBarState,
                inputField = inputField,
                colors =
                    SearchBarDefaults.appBarWithSearchColors(
                        appBarContainerColor = Color.Transparent),
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // 1. Scrollable Tab Row
            PrimaryScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                contentColor = MaterialTheme.colorScheme.primary,
                edgePadding = 0.dp
            ) {
                Tab.entries.forEachIndexed { index, tab ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                // Smoothly scroll pager to the selected tab
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text(tab.title) }
                    )
                }
            }

            // 2. Horizontal Pager for swipeable content
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { pageIndex ->
                // Content for each tab
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Content for ${Tab.entries[pageIndex].title}",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }
    }
}



//
//@OptIn(ExperimentalMaterial3Api::class)
//@Preview
//@Composable
//fun DockedSearchBarScaffoldSample() {
//    val textFieldState = rememberTextFieldState()
//    val searchBarState = rememberSearchBarState()
//    val scope = rememberCoroutineScope()
//    val scrollBehavior = SearchBarDefaults.enterAlwaysSearchBarScrollBehavior()
//    val inputField =
//        @Composable {
//            SearchBarDefaults.InputField(
//                modifier = Modifier,
//                searchBarState = searchBarState,
//                textFieldState = textFieldState,
//                onSearch = { scope.launch { searchBarState.animateToCollapsed() } },
//                placeholder = { Text("Search...") },
//                leadingIcon = {
//                    if (searchBarState.currentValue == SearchBarValue.Expanded) {
//                        TooltipBox(
//                            positionProvider =
//                                TooltipDefaults.rememberTooltipPositionProvider(
//                                    TooltipAnchorPosition.Above
//                                ),
//                            tooltip = { PlainTooltip { Text("Back") } },
//                            state = rememberTooltipState(),
//                        ) {
//                            IconButton(
//                                onClick = { scope.launch { searchBarState.animateToCollapsed() } }
//                            ) {
//                                Icon(
//                                    Icons.AutoMirrored.Default.ArrowBack,
//                                    contentDescription = "Back",
//                                )
//                            }
//                        }
//                    } else {
//                        Icon(Icons.Default.Search, contentDescription = null)
//                    }
//                },
//                trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
//            )
//        }
//    Scaffold(
//        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
//        topBar = {
//            AppBarWithSearch(
//                scrollBehavior = scrollBehavior,
//                state = searchBarState,
//                inputField = inputField,
//                colors =
//                    SearchBarDefaults.appBarWithSearchColors(
//                        appBarContainerColor = Color.Transparent),
//            )
//        },
//    ) { padding ->
//        LazyColumn(contentPadding = padding, verticalArrangement = Arrangement.spacedBy(8.dp)) {
//            val list = List(100) { "Text $it" }
//            items(count = list.size) {
//                Text(
//                    text = list[it],
//                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
//                )
//            }
//        }
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FullScreenSearchBarScaffoldSample() {
    val textFieldState = rememberTextFieldState()
    val searchBarState = rememberSearchBarState()
    val scope = rememberCoroutineScope()
    val scrollBehavior = SearchBarDefaults.enterAlwaysSearchBarScrollBehavior()
    val appBarWithSearchColors = SearchBarDefaults.appBarWithSearchColors()

    val inputField =
        @Composable {
            SearchBarDefaults.InputField(
                modifier = Modifier,
                searchBarState = searchBarState,
                textFieldState = textFieldState,
                onSearch = { scope.launch { searchBarState.animateToCollapsed() } },
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
                                onClick = { scope.launch { searchBarState.animateToCollapsed() } }
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
                },
                trailingIcon = {
                    TooltipBox(
                        positionProvider =
                            TooltipDefaults.rememberTooltipPositionProvider(
                                TooltipAnchorPosition.Above
                            ),
                        tooltip = { PlainTooltip { Text("Mic") } },
                        state = rememberTooltipState(),
                    ) {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(imageVector = Icons.Default.Mic, contentDescription = "Mic")
                        }
                    }
                },
            )
        }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppBarWithSearch(
                scrollBehavior = scrollBehavior,
                state = searchBarState,
                inputField = inputField,
                navigationIcon = {
                    TooltipBox(
                        positionProvider =
                            TooltipDefaults.rememberTooltipPositionProvider(
                                TooltipAnchorPosition.Above
                            ),
                        tooltip = { PlainTooltip { Text("Menu") } },
                        state = rememberTooltipState(),
                    ) {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                },
                actions = {
                    TooltipBox(
                        positionProvider =
                            TooltipDefaults.rememberTooltipPositionProvider(
                                TooltipAnchorPosition.Above
                            ),
                        tooltip = { PlainTooltip { Text("Account") } },
                        state = rememberTooltipState(),
                    ) {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Account",
                            )
                        }
                    }
                },
                colors = appBarWithSearchColors,
            )
            ExpandedFullScreenSearchBar(state = searchBarState, inputField = inputField) {
                SampleSearchResults(
                    onResultClick = { result ->
                        textFieldState.setTextAndPlaceCursorAtEnd(result)
                        scope.launch { searchBarState.animateToCollapsed() }
                    }
                )
            }
        },
    ) { padding ->
        LazyColumn(contentPadding = padding, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            val list = List(100) { "Text $it" }
            items(count = list.size) {
                Text(
                    text = list[it],
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DockedSearchBarScaffoldSample() {
    val textFieldState = rememberTextFieldState()
    val searchBarState = rememberSearchBarState()
    val scope = rememberCoroutineScope()
    val scrollBehavior = SearchBarDefaults.enterAlwaysSearchBarScrollBehavior()
    val appBarWithSearchColors = SearchBarDefaults.appBarWithSearchColors()

    val inputField =
        @Composable {
            SearchBarDefaults.InputField(
                searchBarState = searchBarState,
                textFieldState = textFieldState,
                onSearch = { scope.launch { searchBarState.animateToCollapsed() } },
                placeholder = {
                    if (searchBarState.currentValue == SearchBarValue.Collapsed) {
                        Text(
                            modifier = Modifier.fillMaxWidth().clearAndSetSemantics {},
                            text = "Search",
                            textAlign = TextAlign.Center,
                        )
                    }
                },
            )
        }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppBarWithSearch(
                scrollBehavior = scrollBehavior,
                state = searchBarState,
                inputField = inputField,
                navigationIcon = {
                    TooltipBox(
                        positionProvider =
                            TooltipDefaults.rememberTooltipPositionProvider(
                                TooltipAnchorPosition.Above
                            ),
                        tooltip = { PlainTooltip { Text("Menu") } },
                        state = rememberTooltipState(),
                    ) {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                },
                actions = {
                    TooltipBox(
                        positionProvider =
                            TooltipDefaults.rememberTooltipPositionProvider(
                                TooltipAnchorPosition.Above
                            ),
                        tooltip = { PlainTooltip { Text("Account") } },
                        state = rememberTooltipState(),
                    ) {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Account",
                            )
                        }
                    }
                },
                colors = appBarWithSearchColors,
            )
            ExpandedDockedSearchBar(state = searchBarState, inputField = inputField) {
                SampleSearchResults(
                    onResultClick = { result ->
                        textFieldState.setTextAndPlaceCursorAtEnd(result)
                        scope.launch { searchBarState.animateToCollapsed() }
                    }
                )
            }
        },
    ) { padding ->
        LazyColumn(contentPadding = padding, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            val list = List(100) { "Text $it" }
            items(count = list.size) {
                Text(
                    text = list[it],
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                )
            }
        }
    }
}

@Composable
private fun SampleSearchResults(onResultClick: (String) -> Unit, modifier: Modifier = Modifier) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        repeat(10) { idx ->
            val resultText = "Suggestion $idx"
            ListItem(
                headlineContent = { Text(resultText) },
                supportingContent = { Text("Additional info") },
                leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                modifier =
                    Modifier.clickable { onResultClick(resultText) }
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
            )
        }
    }
}

// Pantallas con parámetro de búsqueda
@Composable
fun HomeScreen(searchQuery: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (searchQuery.isNotEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Resultados para:",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    "\"$searchQuery\"",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        } else {
            Text("Página de Inicio", style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Composable
fun MoviesScreen(searchQuery: String) {
    // Similar a HomeScreen pero con contenido específico de películas
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (searchQuery.isNotEmpty()) {
            Text("Películas que coinciden con: $searchQuery")
        } else {
            Text("Explorar Películas", style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Composable
fun ListsScreen(searchQuery: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (searchQuery.isNotEmpty()) {
            Text("Listas que coinciden con: $searchQuery")
        } else {
            Text("Listas Destacadas", style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Composable
fun FollowingScreen(searchQuery: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (searchQuery.isNotEmpty()) {
            Text("Siguiendo: $searchQuery")
        } else {
            Text("Actividad Reciente", style = MaterialTheme.typography.headlineMedium)
        }
    }
}

// Componente de barra de búsqueda independiente (reutilizable)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LetterboxdSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var active by remember { mutableStateOf(false) }

    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = active,
        onActiveChange = { active = it },
        modifier = modifier,
        placeholder = { Text("Buscar películas, personas...") },
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "Buscar"
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(
                    onClick = { onQueryChange("") }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Limpiar"
                    )
                }
            }
        },
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            dividerColor = Color.Transparent
        )
    ) {
        // Sugerencias de búsqueda
        SearchSuggestions(
            onSuggestionClick = { suggestion ->
                onQueryChange(suggestion)
                active = false
            }
        )
    }
}

@Composable
fun SearchSuggestions(onSuggestionClick: (String) -> Unit) {
    val suggestions = listOf(
        "Drama",
        "Comedia",
        "Acción",
        "Christopher Nolan",
        "Películas de 2023",
        "Clásicos"
    )

    Column {
        Text(
            "Sugerencias",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        suggestions.forEach { suggestion ->
            ListItem(
                headlineContent = { Text(suggestion) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSuggestionClick(suggestion) },
                leadingContent = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            )
        }
    }
}