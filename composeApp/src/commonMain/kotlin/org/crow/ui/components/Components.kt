package org.crow.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.crow.model.Game.CategoryDto
import org.crow.model.Game.CoverDto
import org.crow.model.Game.GameDto
import org.crow.model.ReviewCrowGames
import org.crow.viewModel.GameDetailScreenViewModel
import org.crow.viewModel.GamesScreenViewModel.GameUiState
import kotlin.math.round

@Composable
fun GameHeaderSection(
    modifier: Modifier = Modifier,
    gameUrl: String,
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
                            Color.Transparent,
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.4f),
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
@Composable
fun GameNameDateSection(
    modifier: Modifier = Modifier,
    gameUrl: String,
    name: String,
    gameDate: String,
    developer: String
) {
    Row(
        modifier = Modifier.height(200.dp)
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 10.dp),

        ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.headlineLargeEmphasized,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = gameDate,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
            )
            Text(
                text = developer,
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
    gameDescription: String,
    expanded: Boolean,
    onClickText: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
                .clickable(onClick = onClickText)
        ) {
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
}

//@Composable
//fun SnackBar(){
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(80.dp)
//            .align(Alignment.BottomCenter)
//    ) {
//        // SnackbarHost positioned at the bottom
//        SnackbarHost(
//            hostState = snackbarHostState,
//            modifier = Modifier
//                .align(Alignment.Center)
//                .padding(horizontal = 16.dp),
//            snackbar = { data ->
//                Snackbar(
//                    action = if (showActionButton && data.visuals.actionLabel != null) {
//                        {
//                            TextButton(onClick = { data.performAction() }) {
//                                Text(data.visuals.actionLabel ?: "", color = MaterialTheme.colorScheme.primary)
//                            }
//                        }
//                    } else null,
//                    dismissAction = if (showDismissAction) {
//                        {
//                            IconButton(onClick = { data.dismiss() }) {
//                                Icon(
//                                    imageVector = Icons.Default.Close,
//                                    contentDescription = "Dismiss"
//                                )
//                            }
//                        }
//                    } else null,
//                    actionOnNewLine = actionOnNewLine
//                ) {
//                    Text(data.visuals.message)
//                }
//            }
//        )
//    }
//}
@Composable
fun FilmDetailsTable(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        FilmDetailRow()
        FilmDetailRow()
        FilmDetailRow()
    }
}

@Composable
fun FilmDetailRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Este juego tiene un total de",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "Valor" + "DLCs",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1.5f)
        )
    }
}

@Composable
fun TabExample(
    modifier: Modifier = Modifier,
    tabsList: List<String>,
    selectedTabIndex: Int,
    onClickTab: (Int) -> Unit,
    categorySet: Set<CategoryDto>,
    onClickCategory: (String) -> Unit,
    languages: String,
    onClickLanguage: (String) -> Unit,
    pcRequirements: String,
    covers: List<CoverDto>,
    dlc: List<Int>,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        PrimaryScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.padding(top = 10.dp),
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            edgePadding = 5.dp
        ) {
            tabsList.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onClickTab(index) },
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
            0 -> CategoryTab(
                categorySet = categorySet,
                onClickCategory = onClickCategory
            )

            1 -> DetailsTab(
                languages = languages,
                onLanguageClick = onClickLanguage
            )

            2 -> CoversTab(covers)
            3 -> RequirementsTab(pcRequirements ?: "")
            4 -> DLCTab(dlc)
        }
    }
}

@Composable
fun CategoryTab(
    onClickCategory: (String) -> Unit,
    categorySet: Set<CategoryDto>,
) {
    FlowRow(
        modifier = Modifier.padding(10.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        categorySet.forEach { category ->
            OutlinedCard(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable { onClickCategory(category.description ?: "") },
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
fun DLCTab(dlc: List<Int>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "DLCs Totales",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "${dlc.size}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1.5f)
        )
    }
}

@Composable
fun DetailsTab(
    languages: String,
    onLanguageClick: (String) -> Unit
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
        Column(
        ) {
            if (extractedLanguages.isEmpty()) {
                MinimalLanguageItem(
                    language = "English",
                    onClick = { },
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            } else {
                extractedLanguages.forEach { language ->
                    MinimalLanguageItem(
                        language = language,
                        onClick = { onLanguageClick(language) },
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CoversTab(
    covers: List<CoverDto>,
) {
    FlowRow(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        covers.forEach { game ->
            Item(game.url ?: "")
        }
    }
}

@Composable
fun Item(
    url: String
) {
    Column() {
        AsyncImage(
            model = url,
            contentDescription = "gameDto.shortDescription",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .aspectRatio(2 / 3f)
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.primaryContainer),
        )
    }
}

@Composable
fun FancyTab(title: String, onClick: () -> Unit, selected: Boolean) {
    Tab(
        selected = selected,
        onClick = onClick,
        selectedContentColor = MaterialTheme.colorScheme.primary,
        unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
    ) {
        Column(
            Modifier
                .padding(10.dp)
                .height(50.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = title,
                color = if (selected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        }
    }
}

@Composable
fun Coment() {
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

                // Comentario
                Text(
                    text = "Una película increíble con una fotografía espectacular. La actuación del protagonista es simplemente magistral. Definitivamente una de las mejores del año.",
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 20.sp
                )
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
                            tint = MaterialTheme.colorScheme.primary,
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
                model = "",
                contentDescription = "Portada de la película",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp, 100.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
        }
    }
}

@Composable
fun RatingComponent(
    modifier: Modifier = Modifier,
    metracriticRating: Int,
    averageRating: Double,
    totalRatings: Int,
) {
    Row(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        LetterboxdRatingSection(
            averageRating = averageRating,
            totalRatings = totalRatings
        )
        SimpleStarRating(rating = metracriticRating)
    }
}

@Composable
fun SimpleStarRating(
    modifier: Modifier = Modifier,
    rating: Int,
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
            text = "Metacritic Rating",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}

@Composable
fun LetterboxdRatingSection(
    modifier: Modifier = Modifier,
    averageRating: Double,
    totalRatings: Int,
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = averageRating.roundToTwoDecimals().toString(),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Column {
                RatingStars(
                    rating = averageRating,
                    maxRating = 5,
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

fun Double.roundToTwoDecimals(): Double {
    return round(this * 100) / 100
}

@Composable
fun RatingStars(
    rating: Double,
    maxRating: Int = 5,
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
            )
        }
    }
}

fun extractLanguagesPrecise(input: String): List<String> {
    val pattern = Regex("""([A-Za-z\s-]+)(?:<strong>\*</strong>)""")
    return pattern.findAll(input)
        .map { it.groupValues[1].trim() }
        .toList()
}


@Composable
fun RequirementsTab(
    requirements: String,
) {
    val extractedLanguages = parsePCRequirements(requirements)
    println(extractedLanguages)
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
                imageVector = Icons.Default.Computer,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "PC Requirements",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Column {
            Text(
                text = extractedLanguages,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

fun parsePCRequirements(pcText: String): String {
    val builder = StringBuilder()

    // Verificar si tiene sección RECOMENDADO
    if (pcText.contains("RECOMENDADO:")) {
        // Extraer sección MÍNIMO
        val minimoSection = pcText.substringBefore("RECOMENDADO:")
        val recomendadoSection = pcText.substringAfter("RECOMENDADO:")

        // Parsear MÍNIMO (solo si tiene la lista)
        if (minimoSection.contains("<ul class=\"bb_ul\">")) {
            val minimoItems = extractListItemsAdvanced(minimoSection)
            builder.append("PC Minimum Requirements(1080p/60Hz):\n")
            minimoItems.forEach { item ->
                builder.append("• ${cleanHtmlText(item)}\n")
            }
        } else {
            // Si no tiene lista, usar texto limpio
            builder.append("PC Minimum Requirements (1080p/60Hz):\n")
            builder.append("• ${cleanHtmlText(minimoSection)}\n")
        }

        // Parsear RECOMENDADO (solo si tiene la lista)
        if (recomendadoSection.contains("<ul class=\"bb_ul\">")) {
            val recomendadoItems = extractListItemsAdvanced(recomendadoSection)
            builder.append("\nPC Recommended Requirements (4K/120Hz):\n")
            recomendadoItems.forEach { item ->
                builder.append("• ${cleanHtmlText(item)}\n")
            }
        } else {
            builder.append("\nPC Recommended Requirements (4K/120Hz):\n")
            builder.append("• ${cleanHtmlText(recomendadoSection)}\n")
        }

        // Agregar nota final si existe
        if (recomendadoSection.contains("*Compatible display required")) {
            builder.append("• Nota: Se requiere pantalla compatible para 4K/120Hz\n")
        }
    } else {
        // Si no tiene sección RECOMENDADO, mostrar todo como mínimo
        builder.append("PC Requirements:\n")
        builder.append("• ${cleanHtmlText(pcText)}\n")
    }

    return builder.toString()
}

// Versión simple y compatible con todas las plataformas
private fun extractListItems(html: String): List<String> {
    val items = mutableListOf<String>()
    var remaining = html

    while (remaining.contains("<li") && remaining.contains("</li>")) {
        // Encontrar el siguiente <li
        val liStart = remaining.indexOf("<li")
        if (liStart == -1) break

        // Encontrar el > de cierre de la etiqueta de apertura
        val openTagEnd = remaining.indexOf('>', liStart)
        if (openTagEnd == -1) break

        // Encontrar el </li> correspondiente
        val liEnd = remaining.indexOf("</li>", openTagEnd)
        if (liEnd == -1) break

        // Extraer el contenido
        val content = remaining.substring(openTagEnd + 1, liEnd).trim()
        items.add(content)

        // Continuar con el resto del string
        remaining = remaining.substring(liEnd + 5)
    }

    return items
}

private fun cleanHtmlText(text: String): String {
    var result = text
    var tagStart: Int
    var iterations = 0
    val maxIterations = 100 // Límite para evitar bucle infinito

    do {
        tagStart = result.indexOf('<')
        if (tagStart != -1) {
            val tagEnd = result.indexOf('>', tagStart)
            if (tagEnd != -1) {
                result = result.removeRange(tagStart, tagEnd + 1)
            } else {
                // Etiqueta sin cerrar, eliminar desde < hasta el final
                result = result.removeRange(tagStart, result.length)
                break
            }
        }
        iterations++
    } while (tagStart != -1 && iterations < maxIterations)

    // Limpiar espacios y saltos de línea
    return result.replace("\\s+".toRegex(), " ").trim()
}

private fun extractListItemsAdvanced(html: String): List<String> {
    val result = mutableListOf<String>()

    // Si no contiene etiquetas de lista, devolver el texto completo
    if (!html.contains("<li") && !html.contains("</li>")) {
        // Puede que sea un texto simple o tenga otro formato
        val cleanText = cleanHtmlText(html)
        if (cleanText.isNotBlank()) {
            result.add(cleanText)
        }
        return result
    }

    var position = 0
    val length = html.length

    while (position < length) {
        // Buscar "<li" (case insensitive)
        val liStart = html.findNext("<li", position, ignoreCase = true)
        if (liStart == -1) break

        // Buscar el cierre de la etiqueta de apertura
        val tagEnd = html.indexOf('>', liStart)
        if (tagEnd == -1) break

        // Buscar "</li>" (case insensitive)
        val liEnd = html.findNext("</li>", tagEnd, ignoreCase = true)
        if (liEnd == -1) break

        // Extraer contenido
        val content = html.substring(tagEnd + 1, liEnd).trim()
        if (content.isNotEmpty()) {
            result.add(content)
        }

        position = liEnd + 5
    }

    return result
}

// Función de ayuda para búsqueda case-insensitive
private fun String.findNext(substring: String, startIndex: Int, ignoreCase: Boolean = false): Int {
    return if (ignoreCase) {
        val lowerThis = this.lowercase()
        val lowerSub = substring.lowercase()
        lowerThis.indexOf(lowerSub, startIndex)
    } else {
        this.indexOf(substring, startIndex)
    }
}

@Composable
fun FirstReviews(
    reviews: Set<ReviewCrowGames>,
    onViewAllClick :() -> Unit,
    onWriteReviewClick:() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            Column {
                if(reviews.size<=3){
                    reviews.forEach {
                        ComentGameScreen(it)
                    }
                }else{
                    ComentGameScreen(reviews.elementAt(0))
                    ComentGameScreen(reviews.elementAt(1))
                    ComentGameScreen(reviews.elementAt(2))
                }
               }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
            )
            Row(
                modifier = Modifier.padding(10.dp).height(80.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Card(modifier = Modifier.weight(1f).fillMaxHeight().clickable(onClick = onViewAllClick)) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = "View All Reviews",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.align(Alignment.Center)
                        )

                    }
                }
                Card(modifier = Modifier.weight(1f).fillMaxHeight().clickable(onClick = onWriteReviewClick)) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = "Write Review",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ComentGameScreen(review: ReviewCrowGames) {
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
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row {
                    Text(
                        text = review.username,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1f)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        // Estrellas de rating
                        repeat(review.ratingCrow.rating?:0) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Text(
                            text = review.ratingCrow.rating.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }

                Column(modifier = Modifier.clickable(onClick = { expanded=!expanded })) {
                    AnimatedContent(
                        targetState = expanded,
                        label = "textAnimation"
                    ) { isExpanded ->
                        Text(
                            text = review.reviewCrow.contenido ?: "",
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