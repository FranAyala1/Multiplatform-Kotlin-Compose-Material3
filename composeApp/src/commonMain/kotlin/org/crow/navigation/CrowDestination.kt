package org.crow.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

data class CrowDestination(val name: String, val route: Graph, val icon: ImageVector)

val CrowNavigationTopLevel = listOf(
    CrowDestination(
        icon = Icons.Filled.Home,
        route = Graph.GamesGraph,
        name = "Home"
    ),
    CrowDestination(
        icon = Icons.Filled.Search,
        route = Graph.SearchGamesGraph,
        name = "Search"
    ),
    CrowDestination(
        icon = Icons.Filled.AccountCircle,
        route = Graph.AuthGamesGraph,
        name = "Auth"
    )
)


@Serializable
sealed class Route() {
    @Serializable
    object Games

    @Serializable
    object Search

    @Serializable
    data class GameDetails(val id: Long)

    @Serializable
    object Login

    @Serializable
    data class WriteReview(val url:String,val name:String,val idGame:Long)

    @Serializable
    data class AllReviews(val idGame: Long)
}

sealed class Graph {
    @Serializable
    data object GamesGraph:Graph()

    @Serializable
    data object SearchGamesGraph:Graph()

    @Serializable
    data object AuthGamesGraph:Graph()
}



