package org.crow.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.crow.navigation.Graph
import org.crow.navigation.mainNavHost
import org.crow.ui.bottomBar.BottomBar
import org.crow.ui.topBar.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenIos() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
//            BottomBar(navController = navController)
        },
        topBar = {
//            TopBar(navController = navController)
        }
    ) { innerPadding ->
        mainNavHost(navController, innerPadding)
    }

}


@Composable
actual fun MainScreen(
    onClickBottomBar: (route: Graph) -> Unit,
    currentBackStackEntry: NavBackStackEntry?,
    navController: NavHostController,
    onClickBack: () -> Unit
) {
    MainScreenIos()
}