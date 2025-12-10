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

@Composable
expect fun MainScreen(
    onClickBottomBar: (route: Graph) -> Unit,
    currentBackStackEntry: NavBackStackEntry?,
    navController: NavHostController,
    onClickBack: () -> Unit
)
