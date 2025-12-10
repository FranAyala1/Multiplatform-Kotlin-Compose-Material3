package org.crow.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import org.crow.ui.screens.AuthScreen
import org.crow.ui.screens.GameDetailScreen
import org.crow.ui.screens.GamesScreen
import org.crow.ui.screens.ReviewGamesScreen
import org.crow.ui.screens.ReviewScreen
import org.crow.ui.screens.SearchScreen
import org.crow.ui.screens.UserScreen
import org.crow.viewModel.LoginViewModel
import org.crow.viewModel.ReviewGamesScreenViewModel


@Composable
fun mainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    uiState: LoginViewModel.GameUiState,
) {
    NavHost(navController, startDestination = Graph.GamesGraph) {
        games(navController)
        interest(navController)
        auth(uiState.loggedIn)
    }
}

fun NavGraphBuilder.games(navController: NavHostController) {
    navigation<Graph.GamesGraph>(startDestination = Route.Games) {
        composable<Route.Games> {
            GamesScreen(onClick = { game ->
                navController.navigate(Route.GameDetails(game.id))
            })
        }
        composable<Route.GameDetails> { entry ->
            val gameId = entry.toRoute<Route.GameDetails>()
            GameDetailScreen(
                gameId.id,
                onClickArrowBack = { navController.popBackStack() },
                onClickCategory = {},
                onViewAllClick = {navController.navigate(Route.AllReviews(gameId.id))},
                onWriteReviewClick = { url, name, idGame ->
                    navController.navigate(Route.WriteReview(url, name, idGame))
                },
                onClickSnackBar = {
                    navController.navigate(Route.Login) {
                        popUpTo(Route.Login) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<Route.WriteReview> { entry ->
            val url = entry.toRoute<Route.WriteReview>()
            ReviewScreen(
                url = url.url,
                name = url.name,
                idGame = url.idGame,
                onBack = { navController.popBackStack() },
                onResetToDetailGame = {
                    navController.popBackStack()
                }
            )
        }
        composable<Route.AllReviews> { entry ->
            val gameId = entry.toRoute<Route.AllReviews>()
            ReviewGamesScreen(gameId = gameId.idGame, onBack = {navController.popBackStack()})
        }
    }
}

fun NavGraphBuilder.interest(navController: NavHostController) {
    navigation<Graph.SearchGamesGraph>(startDestination = Route.Search) {
        composable<Route.Search> { SearchScreen(onClick = { game-> navController.navigate(Route.GameDetails(game.id))}) }
    }
}

fun NavGraphBuilder.auth(isLogged: Boolean) {
    navigation<Graph.AuthGamesGraph>(startDestination = Route.Login) {
        composable<Route.Login> {
            if (isLogged) {
                UserScreen()
            } else {
                AuthScreen()
            }

        }
    }
}

