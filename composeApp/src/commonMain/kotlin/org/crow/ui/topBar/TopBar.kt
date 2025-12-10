@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package org.crow.ui.topBar

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination

//Codigo experimental de Material3Api
@Composable
expect fun TopBar(
    onClickNavRailBar: () -> Unit = {},
    currentDestination: NavDestination?,
    onClickBackArrow: () -> Unit
)

@Composable
fun GamesGraphTopBar(navigationIcon: @Composable () -> Unit = {}) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
        ),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 50.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Crow",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        navigationIcon = navigationIcon,
    )
}



@Composable
fun SearchGraphTopBar() {
    var isSearch by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf("") }
    Crossfade(
        modifier = Modifier.animateContentSize(),
        targetState = isSearch,
        label = "Search"
    ) { target ->
        if (!target) {
            TopAppBar(
                title = {
                    IconButton(onClick = { }, modifier = Modifier.fillMaxWidth()) {
                        Row {
                            Text("Search")
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { isSearch = !isSearch }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        } else {
            TopAppBar(
                title = {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = value,
                        placeholder = { Text("Enter card name") },
                        onValueChange = { value = it },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { isSearch = false }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    if (value.isNotBlank()) {
                        IconButton(onClick = { value = "" }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun GameDetailBar(
    title: String
) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )

        // Botones de acción específicos del detalle del juego
        Row {
            IconButton(
                onClick = { /* Acción de favorito */ }
            ) {
                Icon(Icons.Default.FavoriteBorder, "Favorito")
            }
            IconButton(
                onClick = { /* Acción de compartir */ }
            ) {
                Icon(Icons.Default.Share, "Compartir")
            }
        }
    }
}

@Composable
fun NavRailIcon(onClickNavRailBar: () -> Unit) {
    IconButton(onClick = onClickNavRailBar) {
        Icon(
            imageVector = Icons.Filled.Menu,
            contentDescription = "Localized description"
        )
    }
}

@Composable
fun ArrowBackIcon(onClickArrowBack: () -> Unit) {
    IconButton(onClick = onClickArrowBack) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Localized description"
        )
    }
}
