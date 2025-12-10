package org.crow

import androidx.compose.runtime.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import org.crow.navigation.Graph
import org.crow.ui.screens.MainScreen


@Composable
fun App(navController: NavHostController = rememberNavController()) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context).crossfade(enable = true).logger(DebugLogger()).build()
    }
    MainScreen(
        onClickBottomBar = { route ->
            navController.navigate(route) {
                if (Graph.GamesGraph::class == route::class && "org.crow.navigation.Route.Games" == currentBackStackEntry?.destination?.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                } else if((Graph.AuthGamesGraph::class == route::class && "org.crow.navigation.Route.Login" == currentBackStackEntry?.destination?.route)){
                    popUpTo(route) {
                        inclusive = true
                    }
                }else {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                }
            }
        },
        currentBackStackEntry = currentBackStackEntry, navController = navController,
        onClickBack = { navController.popBackStack() }
    )
}